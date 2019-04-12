```
1.git config -l 查看全局用户信息配置

2.git coonfig user.name = "liuburu" 配置用户名

3.git add Hello.java 添加文件到暂存区

4.git commit -m "注释语" 添加文件到工作区

5.git status 查看工作区文件状态

6.git log -pretty=online 版本格式显示

7.git reset --hard head~1 回退到上一个版本

8.git reflog   显示可以穿梭的历史版本信息,以及简写版本信息

9.git reset --hard  e9a40a4  回退到指定的版本

10.git diff head Hello.java 与前一个版本作比较

11.git remote add origin url..  把github与本地仓库相关联

12.git push -u origin master 把本地内容推送到github上

13.git remote -v 查看远程服务器已经存在的版本

14.git remote rm test 删除远程test仓库

15.git commit -a -m "一步提交操作"

16.git clone url  克隆远程仓库

17.git branch brh 创建一个分支

18.git branch 查看分支

19.git checkout brh 切换分支

20.git branch -d brh 删除分支

21.git checkout -b brh 创建并且切换分支

22.git push origin brh 推送分支到远程仓库(远程分支没有该本地分支，也没有关联)
  git push -u origin/bth(推送并且关联)

23.git push origin --delete brh 删除远程分支

24.git branch -m brh newBrh 分支重命名

25.git branch -a/-r/-l  查看全部/远程/本地分支

26.git branch -D newBrh 删除分支(分支还未合并，强制删除)

27.git push origin --delete newBrh / git branch newBrh +git push origin :newBrh 推送空分支


28.Git自动解决冲突与手动解决冲突

29.git log --graph --pretty=oneling  图形显示日志

30.【本地-->远程】
意思：如有没有本地仓库使用第一种方法与远程仓库关联
	如果有本地仓库，那么就是用第二种方法与远程仓库关联
We recommend every repository include a README, LICENSE, and .gitignore.
…or create a new repository on the command line

echo "# gitdemo" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/kakaluote444/gitdemo.git
git push -u origin master
…or push an existing repository from the command line

git remote add origin https://github.com/kakaluote444/gitdemo.git
git push -u origin master
…or import code from another repository
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.

Import code

31.怎么同步远程仓库到本地【远程-->本地】
     
     ·远程分支先创建，如何使让其与本地进行关联，需要使用:
	git branch --set-upstream-to = origin/liuburu

     ·git fetch origin liuburu master:temp
	把远程分支上的liuburu分支抓取到本地的temp分支上
	注意：此时liuburu分支上的数据并未同步到本地，需要
	使用git merge origin/liuburu 进行数据同步


    ·git remote -v 查看远程仓库 
    ·git fetch origin master 把远程仓库orgin的master分支下的代码下到本地的origin/master分之下
    ·git log -p master..origin/master 比较两个版本的差别
    ·git merge origin/master	

    ·git fetch orgin master:temp
    ·git diff temp
    ·git merge temp

     git pull origin master(相当于是从远程获取最新版本并merge到本地)
     
     
     git checkout -b local-branchname origin/remote_branchname拉取远程分支上的代码到本地，并且切换到该分支


32.推送分支到远程分支以及删除远程分支
	
	 git push origin --delete lxh 删除远程分支
	 git branch lxh + git push origin :lxh  推送空分支删除远程分支

 	 git branch -d | -D branchname 删除branchname分支
	 git branch -m|M oldbranch newbranch 重命名分支，M强制重命名
	

33.暂存区的使用	
	git stash 暂存
	
	git pop   回复使用并且删除暂存
	git stash apply 暂存应用恢复
	git stash drop 暂存删除


34.标签的使用
	git tag v1.0 为master定义标签
	git tag v1.2 commitID 未指定版本定义标签
	git show v1.2 查看指定标签
	git tag 查看所有标签
	git tag -a v1.2 -m "this is my tag" commitID添加注释
	git tag -d v1.2 删除本地标签
	git push origin:refs/tags/v1.2 删除远程标签
	git push origin v1.2 推送一个标签
	git push origin -tags 推送多个标签
	git tag -d v1.2
	
	

	

35.Git忽略规则

# 此为注释 – 将被 Git 忽略
*.a       # 忽略所有 .a 结尾的文件
!lib.a    # 但 lib.a 除外
/TODO     # 仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
build/    # 忽略 build/ 目录下的所有文件
doc/*.txt # 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
注意问题：
但是有时候在项目开发过程中，突然心血来潮想把某些目录或文件加入忽略规则，
按照上述方法定义后发现并未生效，原因是.gitignore只能忽略那些原来没有被
track的文件，如果某些文件已经被纳入了版本管理中，则修改.gitignore是无效的。
那么解决方法就是先把本地缓存删除（改变成未track状态），然后再提交：
git rm -r --cached .
git add .
git commit -m 'update .gitignore'


36:多人开发场景（自动决定冲突、手动解决冲突）


1.前提：老板创建好了远程仓库，并且含有readme.md文件,并且创建好了一个公共类型的配置文件config.properties(为了版本演示冲突)

注意：每一个开发者都应该遵循:第一次：克隆->增删改查->提交  以后：拉取->增删改查->提交

刘卜铷：拉取远程项目,创建分支coder_lbrhy并且进行，创建LiuBuRu.java文件进行远程推送

黄  莹：拉取远程项目，切换到coder_lbrhy分支上，创建HuangYing.java文件并且进行远程推送
```

 