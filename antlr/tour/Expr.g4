grammar Expr;

/** 定义规则prog，由多个stat规则组成 */
prog:   stat+ ; 

/** stat规则三种可能 */
stat:   expr NEWLINE               //计算表达式的值换行
    |   ID '=' expr NEWLINE        //赋值语句，将表达式值符给变量
    |   NEWLINE                   //换行
    ;

expr:   expr ('*'|'/') expr   //乘除运算
    |   expr ('+'|'-') expr   //加减运算
    |   INT                   //整数
    |   ID                    //变量
    |   '(' expr ')'          //括号表达式
    ;

/** 词法规则 */
ID  :   [a-zA-Z]+ ;      // 一个或多个字母
INT :   [0-9]+ ;         // 整数
NEWLINE:'\r'? '\n' ;     // 换行
WS  :   [ \t]+ -> skip ; // 空白字符
