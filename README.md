Arcobaleno
此软件为可视化编程软件

1.页面端修改 arcobaleno\demos\index.html 在toolbox中，添加相应的block信息，以及input中field的值。

2.block的js修改 arcobaleno\blocks\相应的类型.js （例如loop.js） 增添相应的block，包括名字、block显示信息、颜色和输入参数。 再修改arcobaleno\blockly_compressed.js 例如loop信息，在55行，添加新增block信息；64行，添加新增的block名字。

3.UI效果 流程：设备A->(设备B->设备C)*5次->设备D

4.相应XML转换：Blockly自带的XML转换可以记录设计区的组件类型、ID、input类型和具体值，如果有衔接关系，用next标签连接。