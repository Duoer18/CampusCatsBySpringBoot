本分支采用mybatis plus（下称mp）代替mybatis，实现功能与master分支一致。
删除三个锁工具类，采用mp提供的乐观锁机制。新建DTO封装猫和记录的全部信息，封装时，
采用如下策略（以猫信息为例，记录封装方式与其相似）：对查找出的Cat对象，获取其
categoryId、colorId、 characterId、locationId，
并再次查找出这些id对应的实际信息，从而封装出完整的猫信息，
而不采用视图或多表的连接。
