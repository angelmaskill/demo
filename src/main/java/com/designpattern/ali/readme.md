一、
score 表
| id | name | score | course  |
| 1  | n1   | 59    | english |
| 2  | n2   | 78    | english |
| 3  | n3   | 85    | math    |
| 4  | n4   | 51    | chinese |
| 5  | n5   | 98    | chinese |
| 6  | n6   | 53    | chinese |
| 7  | n7   | 67    | math    |
| 8  | n8   | 88    | nature  |
| 9  | n9   | 98    | nature  |
| 10 | n10  | 88    | nature  |

class 表
| id | name | className |
| 1  | n1   | A         | 
| 2  | n2   | B         |
| 3  | n3   | C         |
| 4  | n4   | B         |
| 5  | n5   | C         |

1) 总分大于150分，平均分小于90分的人数有几个,单一课程平均分最高的人是多少分。
drop table score;
create table score(id number,name varchar2(20),score number,course varchar2(20));
DROP TABLE CLASS;
CREATE TABLE CLASS (ID NUMBER ,NAME VARCHAR(20),classname CHAR(1));


SELECT COUNT(*) NUM, SUM(T.SCORE) SUMSCORE, AVG(T.SCORE) AVGSCORE
  FROM SCORE T
 GROUP BY NAME
HAVING SUM(T.SCORE) > 150 AND AVG(T.SCORE) < 90;

----

SELECT x.NAME, x.COURSE, MAX(x.AVGSCORE)
  FROM (SELECT T.NAME, T.COURSE, AVG(T.SCORE) AVGSCORE
           FROM SCORE T
          GROUP BY T.COURSE, T.NAME) x
 GROUP BY NAME, COURSE;
 



2) 查询所有班级同学的课程总分。
SELECT T2.ID CLASSID, T1.NAME, SUM(T1.SCORE) SUMSCORE
  FROM SCORE T1, CLASS T2
 WHERE T1.NAME = T2.NAME
 GROUP BY T1.NAME, T2.ID;




二、 给定一个Smartphone类，可以派生如IPhone、AndroidPhone、YunOsPhone类，它还可以是一些有着品牌的手机名称。
1) 如何设计这个类系统？请简单定义这些类系统。
2) 另设计一个充值计费的功能，计算出优惠后的价格，如果是魅族品牌且是YunOs系统手机将优惠1%的价格,如果是苹果手机将优惠5元，如果是中国电信充值则所有类型的手机优惠2%(优惠如有叠加将在优惠后的基础上再次优惠)。

见代码.


三、 给定阶梯定价策略：[0,10000,0.05;10000,1000000,0.04;1000000,+∞,0.02]。0到1万按照5%扣点来收取费用，1万到100万按照4%扣点来收取费用，100万以上按照2%扣点来收取费用。当前累积交易额：9000，任意输入一笔正向或者逆向的交易额，请计算收取的技术服务费用。
   示例：如果给定一笔交易额 2000，收取的费用为：[0到1万] 1000*0.05 + [1万到100万] 1000*0.04
       如果再给定一笔退款2000，退款费用为：[0到1万] 1000*0.05 + [1万到100万] 1000*0.04

见代码.       