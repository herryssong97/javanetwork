DROP TABLE RECORD101;
--
create table RECORD101(
    cd_name varchar2(20), -- �����̸�
    cd_order varchar2(20),-- ����
    cd_price varchar2(20),-- ���ݰ���
    cd_date varchar2(20), -- �����
    cd_soo number(6)   -- ����
);
--select*from RECORD101;

update RECORD101 set cd_soo = 33
where lower(cd_name) like '������'
and cd_order = '������';

select*from RECORD101

where cd_name like '�ڴ�';

commit;

select cd_name, cd_order
from RECORD101
where cd_name like '%����%';

insert into RECORD101 values ('��������' , '�̹���', '19900', '880915', '6');
insert into RECORD101 values ('��������' , '���', '15000', '081105', '6');


--2015-2011

insert into RECORD101 values ('����' , '���', '15000', '150601', '6');

insert into RECORD101 values ('BAE BAE' , '���', '15000', '160114', '23');

insert into RECORD101 values ('LOSER' , '���', '16500', '160114', '1');

insert into RECORD101 values ('CALL ME BABY' , 'EXO', '17000', '150330', '4');

insert into RECORD101 values ('�ڴ�' , '�����̸Ӹ�', '15000', '120307', '77');

insert into RECORD101 values ('SHAKE IT' , '����Ÿ', '17500', '150622', '2');

insert into RECORD101 values ('�͸�����' , '����', '19000', '150528', '3');

insert into RECORD101 values ('������' , '������', '18980', '151023', '5');

insert into RECORD101 values ('��, ��, ��' , '�¾�', '13000', '140603', '33');

insert into RECORD101 values ('�߻�ȭ' , '��ȿ��', '12500', '140328', '27');

insert into RECORD101 values ('�̸��� ������?' , '���̴�', '21000', '130426', '2');

insert into RECORD101 values ('������' , '����', '33000', '140728', '4');

insert into RECORD101 values ('�Ұݵ�' , '������(IU)', '18600', '141002', '7');

insert into RECORD101 values ('1440' , '�㰢', '15000', '130205', '11');

insert into RECORD101 values ('Rose' , '������', '8900', '130328', '9');

insert into RECORD101 values ('My Love' , '�̽�ö', '32700', '130618', '12');

insert into RECORD101 values ('���ϸ�����' , '������', '31000', '130105', '1');

insert into RECORD101 values ('�ź���' , '�ٺ�ġ', '27000', '130304', '2');

insert into RECORD101 values ('�ǵ�����' , '�̽±�', '15500', '121122', '2');

insert into RECORD101 values ('�ȴ�' , '������', '12500', '130103', '4');

commit;

rollback;

select*from RECORD101;


update RECORD101 set cd_soo = '30'
where cd_name = '��������';


select cd_order , count(*)
from RECORD101
where cd_order like '%���%'
group by cd_order;






