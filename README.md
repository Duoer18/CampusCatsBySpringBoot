- 本分支采用 mybatis plus（下称mp）代替 mybatis，实现功能与
master 分支一致。删除三个锁工具类，采用 mp 提供的乐观锁机制。
新建DTO封装猫和记录的全部信息，封装时，采用如下策略
（以猫信息为例，记录封装方式与其相似）：对查找出的 Cat 对象，
获取其 categoryId、colorId、characterId、locationId，
并再次查找出这些id对应的实际信息，从而封装出完整的猫信息，
而不采用视图或多表的连接。
- 请在表 feeding_record_tmp、appearance_record_tmp、cat_tmp
中添加两个列deleted、version，类型均为int（smallint、tinyint
亦可），并为其设置默认值 0 。