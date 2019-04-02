
## 简介

> 博主1994年11月12日，标准的90后，感觉快老了哈。本站点的目的是为了记录博主程序生涯中遇上的一些bug，以及一些工作经验累积。因为人的记忆、热情、经历都是有限的，我们需要一个唤醒我们记忆的空间，让我们随时能够重温那段经验累积的岁月中我们遇见的问题，以及亲密基础的bug以及成功的捷径！总之，做一个平凡的人，追逐一场多彩的梦景!

## 趣味编程
```java
    /**
     * 描述：如果有问题，尽管去问，否则你永远不知道答案是什么
     *
     * @author kiwipeach
     * @email 1099501218@qq.com
     * @wechat lbr2914207499
     * @create 2019-04-01
     */
     public static void main(String[] args) {
        Question yourQuestion = new YourPersonQuestion("怎么学习一门新技术，最近在学SpringBoot要怎么提升自己？");
        System.out.println("你的问题是否得到了解决呢?"+ askQuestion(yourQuestion));
     }
     
    public static boolean askQuestion(Question yourQuestion){
          boolean isQuestionResolved = false;
          while(!isQuestionResolved){
                 if(Baidu.searchResult(yourQuestion)){
                    isQuestionResolved = true;
                    break;
                 }else if(Google.searchResult(yourQuestion)){
                     isQuestionResolved = true;
                    break;
                 }else if(JoinQQGroup("679722876(Java开源博客)")){
                    isQuestionResolved = true;
                     break;
                 }else{
                     //可能通过其他途径再也找不到答案了
                      isQuestionResolved = false;
                      break;
                 }
             }
          return isQuestionResolved;
    }
   
```