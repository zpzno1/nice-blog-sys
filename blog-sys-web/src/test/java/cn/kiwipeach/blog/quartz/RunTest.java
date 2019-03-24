/*
 * Copyright 2019 kiwipeach(1099501218@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.blog.quartz;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.kiwipeach.blog.BlogWebApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 运行测试
 *
 * @author kiwipeach
 * @create 2019-03-22
 */
@Slf4j
public class RunTest extends BlogWebApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private StatisticAnalysisDao statisticAnalysisDao;

    @Before
    public void init() {
        DBTools.setDataSource(dataSource);
    }

    @Test
    public void test() throws Exception {
        CronUtil.schedule("*/2 * * * * *", new Task() {
            @Override
            public void execute() {
                List<AreaStatisticPercent> areaStatisticPercents = statisticAnalysisDao.selectAll();
                List<Boolean> saveOrUpdateList = new ArrayList<Boolean>();
                //更新22个地市的数据
                for (int i = 0; i < areaStatisticPercents.size(); i++) {
                    AreaStatisticPercent areaStatisticPercent = areaStatisticPercents.get(i);
                    String areaCode = areaStatisticPercent.getAreaCode();
                    BigDecimal areaRandomNumber = getAreaRandomNumber(areaCode);
                    //存在跨天问题，需要特殊处理
                    BigDecimal todayCount = getTodayCount(areaStatisticPercent).add(areaRandomNumber);
                    //历史数据无跨天问题
                    BigDecimal historyCount = new BigDecimal(areaStatisticPercent.getHistoryCount()).add(areaRandomNumber);
                    if (statisticAnalysisDao.saveOrUpdate(areaCode, String.valueOf(todayCount), String.valueOf(historyCount))) {
                        saveOrUpdateList.add(true);
                    }
                }
                if (saveOrUpdateList.size() == areaStatisticPercents.size()) {
                    log.warn("定时刷新成功：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } else {
                    log.warn("定时刷新失败：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
            }

            /**
             * 获取地区今日基础数据量，主要是怕有跨天问题
             * @param areaStatisticPercent 当前更新地区信息
             * @return 返回地区基础数据量
             */
            private BigDecimal getTodayCount(AreaStatisticPercent areaStatisticPercent) {
                String hour = statisticAnalysisDao.selectSysHour();
                String minute = statisticAnalysisDao.selectSysMin();
                //跨天了就去数据去配置天数为基础天数
                if ("23".equals(hour) && "59".equals(minute)) {
                    List<AreaStatisticConfig> areaStatisticConfigs = statisticAnalysisDao.selectAreaConfig();
                    for (AreaStatisticConfig statisticConfig : areaStatisticConfigs) {
                        if (statisticConfig.getAreaCode().equals(areaStatisticPercent.getAreaCode())) {
                            String todayCount = statisticConfig.getTodayCount();
                            return new BigDecimal(todayCount);
                        }
                    }
                } else {
                    return new BigDecimal(areaStatisticPercent.getTodayCount());
                }
                return new BigDecimal(0);
            }

            /**
             * 从数据库中获取随机值
             * @param areaCode 地区编码
             * @return 返回地区随机值
             */
            private BigDecimal getAreaRandomNumber(String areaCode) {
                List<AreaStatisticConfig> areaStatisticConfigs = statisticAnalysisDao.selectAreaConfig();
                for (AreaStatisticConfig statisticConfig : areaStatisticConfigs) {
                    if (statisticConfig.getAreaCode().equals(areaCode)) {
                        int[] ints = NumberUtil.generateRandomNumber(statisticConfig.getMin(), statisticConfig.getMax(), 1);
                        log.warn("地区：{} 随机值：{}", statisticConfig.getAreaCodeName(), ints[0]);
                        return new BigDecimal(ints[0]);
                    }
                }
                return new BigDecimal(0);
            }
        });
        //支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
        System.in.read();
    }
}
