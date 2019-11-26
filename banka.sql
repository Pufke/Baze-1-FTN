create table klijent (
idk integer not null,
imek varchar (20) not null,
przk varchar (20) not null,
god date not null, constraint klijent_pk primary key (idk)
);
create table tipkredita (
idt varchar(3) not null,
nazt varchar (20) not null,
procenat number not null,
constraint tipkredita_pk primary key (idt)
);
create table banka (
idb varchar (3) not null,
nazb varchar (20) not null,
drziso varchar (3) not null,
constraint banka_pk primary key (idb)
);
create  table kredit (
idkr integer not null,
klijent integer not null,
banka varchar (3) not null,
tip varchar (3) not null,
trazizn number not null,
odobizn number not null,
val varchar(3) not null,
constraint kredit_pk primary key (idkr),
constraint klijent_fk foreign key (klijent) references klijent(idk), 
constraint banka_fk foreign key (banka) references banka(idb), 
constraint tipkredita_fk foreign key (tip) references tipkredita(idt)
);

drop table kredit;

insert into klijent (idk, imek, przk, god) values (100, 'Petar', 'Petrovic', '10-Jan-1950');
insert into klijent (idk, imek, przk, god) values (200, 'Jovan', 'jovanovic', '4-Mar-1960');
insert into klijent (idk, imek, przk, god) values (300, 'Nikola', 'Nikolic', '5-Jun-1970');
insert into klijent (idk, imek, przk, god) values (400, 'Ivan', 'ivanovic', '1-Aug-1980');
insert into klijent (idk, imek, przk, god) values (500, 'Ana', 'Nikolic', '2-Sep-1985');
insert into klijent (idk, imek, przk, god) values (600, 'Petar', 'petrovic', '10-Jan-1950');
insert into klijent (idk, imek, przk, god) values (700, 'Jovan', 'jovanovic', '4-Mar-1960');
insert into klijent (idk, imek, przk, god) values (800, 'Nikola', 'Nikolic', '5-Jun-1970');
insert into klijent (idk, imek, przk, god) values (900, 'Ivan', 'ivanovic', '1-Aug-1980');
insert into klijent (idk, imek, przk, god) values (1000, 'Ana', 'Nikolic', '2-Sep-1985');
insert into klijent (idk, imek, przk, god) values (1100, 'Ivana', 'Peric', '3-oct-1975');

insert into tipkredita (idt, nazt, procenat) values ('ti', 'Gotovinski', 10);
insert into tipkredita (idt, nazt, procenat) values ('T2', 'stambeni', 8);
insert into tipkredita (idt, nazt, procenat) values ('13', 'Auto', 12);
insert into tipkredita (idt, nazt, procenat) values ('14', 'poljoprivredni', 9);

insert into banka (idb, nazb, drziso) values ('61', 'BancaIntesa', 'ITA');
insert into banka (idb, nazb, drziso) values ('B2', 'Erste Bank', 'AUT');
insert into banka (idb, nazb, drziso) values ('63', 'Raiffeisen', 'AUT');
insert into banka (idb, nazb, drziso) values ('34', 'AIK', 'SRB');
insert into banka (idb, nazb, drziso) values ('85', 'Societe Generale', 'FRA');

insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (1, 100, '34', '13', 10000, 9700, 'USD');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (2, 200, '34', '13', 5000, 2000, 'USD');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (3, 300, '63', 'ti',3000, 3000, 'EUR');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (4, 400, '34', '13',4000, 3000 , 'CHF');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (5, 500, '85', 'T2',20000, 19000, 'CHF');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (6, 600, '85', '14',10000, 9000 , 'USD');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (7, 700, '63', 'T2',8000, 8000 , 'EUR' );
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (8, 800, '61', 'T2' ,1000, 500 , 'EUR');
insert into kredit (idkr, klijent, banka, tip, trazizn, odobizn, val) values (9, 900, '61', 'T2',10000, 10000, 'CHE');


--1. Izlistati imena i prezime svih klijenata sortirano po imenu i prezimenu.
select imek,przk from klijent order by imek,przk;

--2. Izlistati nazive tipova kredita gde je procenat izmedju 2 i 10%.
select nazt from tipkredita where procenat between 2 and 10;

--3. Dodati ogranicenje da klijent mora biti rodjen posle 1950.
alter table klijent 
ADD CONSTRAINT data_check CHECK( god > to_date('01-01-1950' , 'dd-mm-yyyy'));

--4. Izlistati imena i prezimena klijenata i odobrenu sumu, za klijente koji su podigli kredit u USD valuti, 
--a da im je odobreno vise od proseka USD kredita.
select imek,przk, kr.odobizn, ROUND(avg(kr2.odobizn),2) as prosekUSD
from klijent kl,kredit kr,kredit kr2
where kl.idk = kr.klijent and kr.val = 'USD' and kr2.val = 'USD' 
group by imek,przk,kr.odobizn
having kr.odobizn>avg(kr2.odobizn);

--5. Ispisati klijente koji su digli kredit u Erste banci, a nisu u Intesa banci.
select k.idk, k.imek, k.przk from klijent k where 
k.idk in(select klijent 
         from kredit kr,banka b 
         where kr.banka = b.idb and b.nazb = 'BancaIntesa')
and
k.idk not in(select klijent 
            from kredit kr,banka b 
            where kr.banka = b.idb and b.nazb = 'Erstee Bank');

--6. Ispisati sve banke u kojima je kredite diglo vise od 3 klijenta (racuna se kada je jedna osoba digla 2 ili vise kredita).
select b.nazb,count(banka) as brKredita
from kredit k,banka b
where b.idb = k.banka
group by b.nazb
having count(banka)>=2;

--8. Svim klijentima rodjenim pre 1960 dodati * na kraj prezimena.
UPDATE klijent set przk = przk || '*' where god < '01-JAN-1960';

UPDATE klijent set przk = TRIM(TRAILING '*' from przk); 
--9. Napraviti pogled koji sadrzi tip kredita, prosecno trazeno, prosecno odobreno za one tipove za koje je suma
--odobrenog manja od 30000. (Ukljuciti tipove koji nemaju odobrene kredite i za njih staviti proseke 0)
create or replace view inf_o_kreditima (tipKr, prosTrazeno, prosOdobreno) as 
select kr1.tip, ROUND(avg(kr2.trazizn),2), ROUND(avg(kr2.odobizn),2)
from kredit kr1,kredit kr2
group by kr1.tip
having sum(kr2.odobizn) > 30000;

--10. Klijenti su podeljeni na grupe po godinama 1950-1965, 1966-1985, posle 1985. Za svaku grupu ispisati koliko kredita 
--je podigla, samo ako je grupa trazila vise od 9000 ukupno.
create or replace view grupa_1950do1965 (brKr,tr_izn) as
select count(kr.idkr),sum(kr.trazizn) 
from klijent k,kredit kr 
where kr.klijent = k.idk and god between '01-JAN-1950' and '31-DEC-1965'; 

create or replace view grupa_1966do1985 (brKr,tr_izn) as
select count(kr.idkr),sum(kr.trazizn) 
from klijent k,kredit kr 
where kr.klijent = k.idk and god between '01-JAN-1966' and '31-DEC-1985';

create or replace view grupa_od1985 (brKr,tr_izn) as
select count(kr.idkr),sum(kr.trazizn) 
from klijent k,kredit kr 
where kr.klijent = k.idk and god > '31-dec-1985'; 

select brKr,tr_izn from grupa_1950do1965 where tr_izn > 9000
union all
select brKr,tr_izn from grupa_1966do1985 where tr_izn > 9000
union all
select brKr,tr_izn from grupa_od1985 where tr_izn > 9000;