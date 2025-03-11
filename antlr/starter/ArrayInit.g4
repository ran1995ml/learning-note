grammar ArrayInit;

/** 名为init的规则，匹配一对花括号中逗号分隔的value */
init : '{' value (',' value)* '}' ;

/** 一个value可以是嵌套的花括号，也可以是简单的整数 */
value : init
      | INT
      ;

//语法解析规则以小写开头，词法分析以大写开头
INT : [0-9]+ ;  //定义词法符号
WS : [ \t\r\n]+ -> skip ; //词法规则丢弃空白符号
