grammar Hello;             //定义名为Hello的语法
r : 'hello' ID ;           //匹配关键字hello和紧随的标识符
ID : [a-z]+ ;              //匹配小写字母组成的标识符
WS : [ \t\r\n]+ -> skip;   //忽略空格、换行、TAB