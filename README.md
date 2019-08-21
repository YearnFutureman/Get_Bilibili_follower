# Get_Bilibili_follower
获取B站粉丝量等信息

个人抓取了下B站个人信息和Api接口

/**
  * B站接口：
  */
  https://api.bilibili.com/x/web-interface/online //获取B站在线人数 
  https://api.bilibili.com/x/relation/stat?vmid=(UP的id) 获取个人粉丝量 和关注人数
  https://api.bilibili.com/x/space/acc/info?mid=（UP的id）获取up个人信息
  
  个人直接用计时器 每秒刷新一次 更新 B站在线人数和UP主的粉丝
  利用Gson 解析 Json 
  利用了Htextview 比较炫酷的Textview的demo来显示(https://github.com/hanks-zyh/HTextView) 这是他的Github Htextview 实例
  
