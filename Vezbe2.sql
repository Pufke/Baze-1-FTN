--Prikazati min i max platu radnika
select max(plt) minimalna, min(plt) maksimalna
from radnik;
--Prikazati broj radnika i ukupnu mesecnu platu radnika 
select count(mbr) broj_radnika,
sum(plt) as "Ukupna mesecna plt"
from radnik;
--Prikazati koliko radnika radi na svakom projektu i koliko je ukupno angažovanje na tom projektu?
select spr "Sifra projekta", count(mbr) "Broj radnika na projektu", sum(brc) "Ukupno angazovanje(br casova)"
from radproj
group by spr;
--Prikazati broj radnika , prose?nu platu i
--ukupnu godišnju platu svih radnika .
select count(*) as "Broj radnika",
round(avg(plt),2) as "Prosecna plata", 
12*sum(plt) as "Godisnja plt svih radnika"
from radnik;
--Prikazati ukupnu premiju svih radnika ?iji
--je mati?ni broj ve ?i od 100.
select sum(pre)
from radnik 
where mbr>100;
--Prikazati prose?nu platu svih radnika
--pomnoženu sa koren iz 2 (1 41) zaokruženo na dve decimale.
select round(avg(plt)*sqrt(2), 2) as "prosecna plt svih rad"
from radnik;
--Select naredba u listi tabela
select * from (select mbr,ime from radnik);

--Prikazati 10 radnika koji imaju najve?u platu ,
--sortiranih po plati u opadaju?em redosledu
select mbr,plt,rownum
from (select * from radnik order by plt desc)
where rownum <= 10;

--Prikazati za svakog radnika red koji sadrži
--njegovu platu, prose?nu platu i 
--apsolutnu (ABS) razliku prose?ne plate i njegove plate.
select plt , (select round(avg(plt),2) from radnik) as "Prosecna plata",
abs((select round(avg(plt),2) from radnik)-plt) as razlika
from radnik;

--group by UVOD
select mbr, spr
from radproj
where mbr < 40;

select mbr, count(spr) 
from radproj
where mbr<40 
group by mbr;

--Prikazati koliko radnika radi na svakom
--projektu i koliko je ukupno angažovanje na
--tom projektu?
select spr, count(mbr), sum(brc)
from radproj
group by spr;

--Izlistati mbr radnika koji rade na više od dva
--projekta, pored mbr a, prikazati i broj projekata
--na kojima radnici rade.
select mbr from radproj
group by mbr
having count(spr)>2;

--Izlistati u rastu?em redosledu plate mbr,
--ime, prz i plt radnika koji imaju platu ve?u
--od prose?ne.
select mbr, ime, prz, plt
from radnik
where plt>(select avg(plt) from radnik)
order by plt asc;

--Izlistati imena i prezimena radnika koji
--rade na projektu sa šifrom 30

select mbr,ime,prz
from radnik 
where mbr in(select mbr from radproj where spr=30);

--Izlistati mbr, ime, prz radnika koji rade na
--projektu sa šifrom 10, a ne rade na projektu sa
--šifrom 30.
select mbr,ime,prz
from radnik 
where mbr in(select mbr from radproj where spr = 10) and
mbr not in(select mbr from radproj where spr = 30);

--Izlistati mbr radnika koji rade na više od dva projekta.
select mbr as "radnici na vise od dva proj",
count(spr)
from radproj 
group by mbr
having count(spr)>2;
--Izlistati u rastu?em redosledu plate mbr, ime, prz i plt radnika koji imaju platu ve?u od prose?ne.
select mbr, ime, prz, plt
from radnik
where plt>(select avg(plt) from radnik)
order by plt ASC;
--Izlistati imena i prezimena radnika koji rade na projektu sa šifrom 30 (pomo?u ugnježdenog upita).
select ime,prz
from radnik 
where mbr in(select mbr from radproj where spr=30);
--Izlistati mbr, ime, prz radnika koji rade na projektu sa šifrom 10, a ne rade na projektu sa šifrom 30.
select mbr, ime, prz
from radnik
where mbr in
(select mbr from radproj where spr=10)
and mbr not in
(select mbr from radproj where spr=30);
--Izlistati ime, prz i god najstarijeg radnika.
select ime,prz,god
from radnik
where god <= all(select god from radnik);
--DRUGI NACIN SA AGREGATCKOM F-jom
select ime,prz,god 
from radnik 
where god = all(select min(god) from radnik);
--Prikazati mbr, prz, ime, plt i brc angažovanja svih radnika koji rade na projektu sa šifrom 10.
select mbr,prz,ime,plt
from radnik
where mbr in(select mbr,plt from radproj where spr=10);
--DRUGI NACIN
select radnik.mbr,prz,ime,plt,brc
from radnik,radproj
where spr=10 and 
radnik.mbr = radproj.mbr;

--Izlistati ime, prz i god najstarijeg radnika.
select ime,prz,god 
from radnik 
where god = (select min(god) from radnik);

--Prikazati mbr, prz, ime, plt i brc
--angažovanja svih radnika koji rade na projektu sa šifrom 10.
select radnik.mbr, prz, ime, plt , brc
from radnik,radproj
where spr = 10 and 
radnik.mbr = radproj.mbr;

--Prikazati mbr, ime, prz i plt radnika koji su rukovodioci projekata.
select mbr, ime, prz, plt 
from radnik, projekat
where ruk=mbr;

--Izlistati imena, prezimena svih radnika osim rukovodioca projekta sa šifrom 10.
select mbr,ime,prz 
from radnik r, projekat p
where p.spr = 10 and r.mbr != p.ruk;

--Prikazati imena i prezimena rukovodilaca
--projekata i broj projekata kojima rukovode.
select ime,prz,count(spr)
from radnik r,projekat p
where ruk=mbr
group by mbr,prz,ime;

