--Izlistati sadraj svih tabela.
select * FROM radnik; 
select * FROM projekat;
select * FROM radproj;

-- Prikazati imena i prezimena svih radnika.
select ime,prz FROM radnik;

--Izlistati razli?ita imena radnika.
select DISTINCT ime FROM radnik;

--Izlistati mbr, ime i prezime radnika koji imaju platu ve?u od 25000.
select mbr,ime,prz FROM radnik WHERE plt>25000; 

--Izlistati godišnju platu svakog radnika.
select mbr,ime,prz,plt*12 as PLATA FROM radnik;

-- Izlistati mbr, ime, prz radnika koji nemaju šefa. 
select mbr,ime,prz from RADNIK where sef is NULL;

--ispisati radnika koji imaju sefa 
select mbr,ime,prz from RADNIK where sef is not NULL;

--Izlistati mbr, ime, prz radnika ?ija je plata izme?u 20000 i 24000 dinara.
select mbr,ime,prz,plt from radnik where  plt >= 20000 and plt <=  24000;
select mbr,ime,prz,plt from radnik where plt between 20000 and 24000;

--Izlistati mbr, ime, prz radnika ?ija je plata nije izme?u 20000 i 24000 dinara.
select mbr,ime,prz,plt from radnik where  plt > 20000 or plt <  24000;
select mbr,ime,prz,plt from radnik where plt not between 20000 and 24000;

--Izlistati ime, prz, god radnika ro?enih izme?u 1953 i 1975. 
select ime,prz,god from radnik where god between '01-jan-1953' and '31-dec-1975';

--Izlistati ime, prz, god radnika koji nisu ro?eni izme?u 1953 i 1975.
select ime,prz,god from radnik where god not between '01-jan-1953' and '31-dec-1975';

--Izlistati mbr, ime, prz radnika ?ije prezime po?inje na slovo M. 
select mbr,ime,prz from radnik where prz like 'M%';

--Izlistati mbr, ime, prz radnika ?ije ime ne po?inje slovom A. 
select mbr,ime,prz from radnik where ime not like 'A%';

--Izlistati mbr, ime, prz radnika ?ije ime sadri slovo a na drugoj poziciji. 
select mbr,ime,prz from radnik where ime like '_a%';

--Izlistati imena radnika koja po?inju na slovo E. Imena ne bi trebalo da se ponavljaju. 
select distinct ime from radnik where ime like 'E%';

--Izlistati radnike koji u svom imenu imaju slovo E (e)
select ime,prz from radnik where ime like '%e%' or ime like '%E%';

--Izlistati mati?ne brojeve radnika koji rade na projektima sa šifrom 10, 20 ili 30. 
select distinct mbr from radproj where spr in (10,20,30);

--or radi kao po tautologiji znaci izlistace akda je jedno ili drugo tacno
select distinct mbr from radproj where brc in(2,4,6) or spr='10';
--na kolokvijumu moramo da proverimo uvek da li je dobro 

--Izlistati mati?ne brojeve radnika koji se ne zovu Ana ili Sanja.
select mbr,ime,prz from radnik where ime not in ('Ana', 'Sanja');

-- Prikazati radnike koji imaju šefa sortirano po prezimenu. 
select mbr,ime,prz 
from radnik 
where sef is not null 
order by prz asc;

--Prikazati mati?ne brojeve, imena, prezimena i plate radnika, po opadaju?em redosledu iznosa plate. 
select mbr,ime,prz,plt plata 
from radnik
order by plata desc;

--Prikazati mati?ne brojeve, spojena(konkatenirana) imena i prezimena radnika, kao i plate, uve?ane za 17%.  
select mbr,ime||' '||prz,plt*1.17 as Plata
from radnik;

--Prikazati mati?ne brojeve, spojena(konkatenirana) imena i prezimena radnika, kao i plate, uve?ane za 17%. 
--Uz pomoc concat f-je
select mbr, concat(concat(ime, ' '),prz), plt*1.17 as plata
from radnik;

--selektuj sve radnike cije prezime sadrzi ime (npr Marko Markovic)
select * 
from radnik 
where LOWER(prz) LIKE  '%'||LOWER(ime)||'%';

--Prikazati mati?ne brojeve radnika, imena i prezimena i platu radnika koji se zovu Pera ili Moma. 
select mbr,ime,prz,plt 
from radnik 
where ime = ANY ('Pera', 'Moma');

--Prikazati mati?ne brojeve radnika, imena i prezimena i platu radnika koji se ne zovu Pera ili Moma.
select mbr, ime, prz, plt 
from radnik 
where ime != ALL('Pera', 'Moma');

--Prikazati mati?ne brojeve radnika, kao i plate, uve?ane za NULL vrednost. 
select mbr,plt + NULL 
from radnik;

--Prikazati mati?ne brojeve radnika, kao i plate, uve?ane za godišnju premiju. 
select mbr, plt + pre
from radnik;

--Prikazati mati?ne brojeve radnika, kao i plate, uve?ane za godišnju premiju. Ukoliko za nekog radnika vrednost premije 
--ne postoji, smatrati da ona iznosi 0. 
select mbr, plt + NVL(pre,0) as "Plata uvecana za premiju"
from radnik;

--Koliko ima radnika?
select count(*)
from radnik;

-- Koliko ima šefova? 
select count(distinct sef) as "broj sefova"
from radnik; --Mora DISTINCT zato sto jedan radnik moze da bude sef na vise projekata