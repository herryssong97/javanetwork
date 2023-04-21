DROP TABLE RECORD101;
--
create table RECORD101(
    cd_name varchar2(20), -- 음반이름
    cd_order varchar2(20),-- 가수
    cd_price varchar2(20),-- 음반가격
    cd_date varchar2(20), -- 출시일
    cd_soo number(6)   -- 수량
);
--select*from RECORD101;

update RECORD101 set cd_soo = 33
where lower(cd_name) like '스물셋'
and cd_order = '아이유';

select*from RECORD101

where cd_name like '자니';

commit;

select cd_name, cd_order
from RECORD101
where cd_name like '%붉은%';

insert into RECORD101 values ('붉은노을' , '이문세', '19900', '880915', '6');
insert into RECORD101 values ('붉은노을' , '빅뱅', '15000', '081105', '6');


--2015-2011

insert into RECORD101 values ('뱅뱅뱅' , '빅뱅', '15000', '150601', '6');

insert into RECORD101 values ('BAE BAE' , '빅뱅', '15000', '160114', '23');

insert into RECORD101 values ('LOSER' , '빅뱅', '16500', '160114', '1');

insert into RECORD101 values ('CALL ME BABY' , 'EXO', '17000', '150330', '4');

insert into RECORD101 values ('자니' , '프라이머리', '15000', '120307', '77');

insert into RECORD101 values ('SHAKE IT' , '씨스타', '17500', '150622', '2');

insert into RECORD101 values ('와리가리' , '혁오', '19000', '150528', '3');

insert into RECORD101 values ('스물셋' , '아이유', '18980', '151023', '5');

insert into RECORD101 values ('눈, 코, 입' , '태양', '13000', '140603', '33');

insert into RECORD101 values ('야생화' , '박효신', '12500', '140328', '27');

insert into RECORD101 values ('이름이 뭐예요?' , '포미닛', '21000', '130426', '2');

insert into RECORD101 values ('빨개요' , '현아', '33000', '140728', '4');

insert into RECORD101 values ('소격동' , '아이유(IU)', '18600', '141002', '7');

insert into RECORD101 values ('1440' , '허각', '15000', '130205', '11');

insert into RECORD101 values ('Rose' , '이하이', '8900', '130328', '9');

insert into RECORD101 values ('My Love' , '이승철', '32700', '130618', '12');

insert into RECORD101 values ('강북멋쟁이' , '정형돈', '31000', '130105', '1');

insert into RECORD101 values ('거북이' , '다비치', '27000', '130304', '2');

insert into RECORD101 values ('되돌리다' , '이승기', '15500', '121122', '2');

insert into RECORD101 values ('싫다' , '백지영', '12500', '130103', '4');

commit;

rollback;

select*from RECORD101;


update RECORD101 set cd_soo = '30'
where cd_name = '붉은노을';


select cd_order , count(*)
from RECORD101
where cd_order like '%빅뱅%'
group by cd_order;






