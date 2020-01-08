#include "operacije_nad_datotekom.h"

FILE *otvoriDatoteku(char *filename) {
	FILE *fajl = fopen(filename, "rb+");
	if (fajl == NULL) {
		printf("Doslo je do greske! Moguce da datoteka \"%s\" ne postoji.\n", filename);
	} else {
		printf("Datoteka \"%s\" otvorena.\n", filename);
	}
	return fajl;
}

void kreirajDatoteku(char *filename) {
	FILE *fajl = fopen(filename, "wb");
	if (fajl == NULL) {
		printf("Doslo je do greske prilikom kreiranja datoteke \"%s\"!\n", filename);
	} else {
		//upisi pocetni blok
		BLOK blok;
		strcpy(blok.slogovi[0].evidBroj, OZNAKA_KRAJA_DATOTEKE);
		fwrite(&blok, sizeof(BLOK), 1, fajl);
		printf("Datoteka \"%s\" uspesno kreirana.\n", filename);
		fclose(fajl);
	}
}

SLOG *pronadjiSlog(FILE *fajl, char *evidBroj) {
	if (fajl == NULL) {
		return NULL;
	}

	fseek(fajl, 0, SEEK_SET);
	BLOK blok;

	while (fread(&blok, sizeof(BLOK), 1, fajl)) {

		for (int i = 0; i < FBLOKIRANJA; i++) {
			if (strcmp(blok.slogovi[i].evidBroj, OZNAKA_KRAJA_DATOTEKE) == 0) {
				//nema vise slogova
				return NULL;
			}

			if (strcmp(blok.slogovi[i].evidBroj, evidBroj) == 0) {

                if (blok.slogovi[i].deleted) {
                    //logicki obrisan slog, kao i da ne postoji
                    return NULL;
                }

				SLOG *slog = (SLOG *)malloc(sizeof(SLOG));
				memcpy(slog, &blok.slogovi[i], sizeof(SLOG));
				return slog;
			}
		}
	}

	return NULL;
}

void dodajSlog(FILE *fajl, SLOG *slog) {
	if (fajl == NULL) {
		printf("Datoteka nije otvorena!\n");
		return;
	}

	SLOG *slogStari = pronadjiSlog(fajl, slog->evidBroj);
	if (slogStari != NULL && !slogStari->deleted) {
        //u datoteci vec postoji slog sa tim evid. brojema i NIJE izbrisan,
        //pa ne mozemo upisati novi slog
        printf("Vec postoji slog sa tim evid brojem!\n");
        return;
    }

	BLOK blok;
	fseek(fajl, -sizeof(BLOK), SEEK_END); //u poslenji blok upisujemo novi slog
	fread(&blok, sizeof(BLOK), 1, fajl);

	int i;
	for (i = 0; i < FBLOKIRANJA; i++) {
		if (strcmp(blok.slogovi[i].evidBroj, OZNAKA_KRAJA_DATOTEKE) == 0) {
            //Ovo je mesto gde se nalazi slog sa oznakom
            //kraja datoteke; tu treba upisati novi slog.
			memcpy(&blok.slogovi[i], slog, sizeof(SLOG));
			break;
		}
	}

	i++; //(na to mesto u bloku cemo upisati krajDatoteke)

	if (i < FBLOKIRANJA) {
        //Jos uvek ima mesta u ovom bloku, mozemo tu smestiti slog
        //sa oznakom kraja datoteke.
		strcpy(blok.slogovi[i].evidBroj, OZNAKA_KRAJA_DATOTEKE);

		//Sada blok mozemo vratiti u datoteku.
		fseek(fajl, -sizeof(BLOK), SEEK_CUR);
		fwrite(&blok, sizeof(BLOK), 1, fajl);
	} else {
		//Nema vise mesta u tom bloku, tako da moramo
        //praviti novi blok u koji cemo upisati slog
        //sa oznakom kraja datoteke.

		//Prvo ipak moramo vratiti u datoteku blok
        //koji smo upravo popunili:
		fseek(fajl, -sizeof(BLOK), SEEK_CUR);
		fwrite(&blok, sizeof(BLOK), 1, fajl);

		//Okej, sad pravimo novi blok:
		BLOK noviBlok;
		strcpy(noviBlok.slogovi[0].evidBroj, OZNAKA_KRAJA_DATOTEKE);
		//(vec smo na kraju datoteke, nema potrebe za fseekom)
		fwrite(&noviBlok, sizeof(BLOK), 1, fajl);
	}

	if (ferror(fajl)) {
		printf("Greska pri upisu u fajl!\n");
	} else {
		printf("Upis novog sloga zavrsen.\n");
	}
}

void ispisiSveSlogove(FILE *fajl) {
	if (fajl == NULL) {
		printf("Datoteka nije otvorena!\n");
		return;
	}

	fseek(fajl, 0, SEEK_SET);
	BLOK blok;
	int rbBloka = 0;
	printf("BL SL Evid.Br   Sif.Zat      Dat.Vrem.Dol  Celija  Kazna\n");
	while (fread(&blok, sizeof(BLOK), 1, fajl)) {

		for (int i = 0; i < FBLOKIRANJA; i++) {
			if (strcmp(blok.slogovi[i].evidBroj, OZNAKA_KRAJA_DATOTEKE) == 0) {
				printf("B%d S%d *\n", rbBloka, i);
                break; //citaj sledeci blok
			}


			if (!blok.slogovi[i].deleted) {
                printf("B%d S%d ", rbBloka, i);
                ispisiSlog(&blok.slogovi[i]);
                printf("\n");
            }
		}

		rbBloka++;
	}
}

void ispisiSlog(SLOG *slog) {
	printf("%8s  %7s  %02d-%02d-%4d %02d:%02d %7s %6d",
        slog->evidBroj,
		slog->sifraZatvorenika,
		slog->datumVremeDolaska.dan,
		slog->datumVremeDolaska.mesec,
		slog->datumVremeDolaska.godina,
		slog->datumVremeDolaska.sati,
		slog->datumVremeDolaska.minuti,
		slog->oznakaCelije,
		slog->duzinaKazne);
}

void azurirajSlog(FILE *fajl, char *evidBroj, char *oznakaCelije, int duzinaKazne) {
	if (fajl == NULL) {
		printf("Datoteka nije otvorena!\n");
		return;
	}

	fseek(fajl, 0, SEEK_SET);
	BLOK blok;
	while (fread(&blok, sizeof(BLOK), 1, fajl)) {

		for (int i = 0; i < FBLOKIRANJA; i++) {
            if (strcmp(blok.slogovi[i].evidBroj, OZNAKA_KRAJA_DATOTEKE) == 0) {
                printf("Slog koji zelite menjati ne postoji!\n");
                return;
            }

			if (strcmp(blok.slogovi[i].evidBroj, evidBroj) == 0) {

                //azuriraj oznaku celije i duzinu kazne
				strcpy(blok.slogovi[i].oznakaCelije, oznakaCelije);
				blok.slogovi[i].duzinaKazne = duzinaKazne;

				fseek(fajl, -sizeof(BLOK), SEEK_CUR);
				fwrite(&blok, sizeof(BLOK), 1, fajl);
				if (ferror(fajl)) {
					printf("Greska pri upisu u fajl!\n");
				} else {
					printf("Slog izmenjen.\n");
				}
				return;
			}
		}
	}
}

void obrisiSlogLogicki(FILE *fajl, char *evidBroj) {
	if (fajl == NULL) {
		printf("Datoteka nije otvorena!\n");
		return;
	}

	fseek(fajl, 0, SEEK_SET);
	BLOK blok;
	while (fread(&blok, sizeof(BLOK), 1, fajl)) {

		for (int i = 0; i < FBLOKIRANJA; i++) {
			if (strcmp(blok.slogovi[i].evidBroj, evidBroj) == 0) {
				if (blok.slogovi[i].deleted == 1) {
					printf("Slog je vec logicki obrisan!\n");
					return;
				}
				blok.slogovi[i].deleted = 1;
				fseek(fajl, -sizeof(BLOK), SEEK_CUR);
				fwrite(&blok, sizeof(BLOK), 1, fajl);
				if (ferror(fajl)) {
					printf("Doslo je do greske pri brisanju (fwrite)!\n");
				} else {
					printf("Brisanje sloga zavrseno.\n");
				}
				return;
			}
		}
	}
}

void obrisiSlogFizicki(FILE *fajl, char *evidBroj) {
	if (fajl == NULL) {
		printf("Datoteka nije otvorena!\n");
		return;
	}

	//Krenucemo od kraja datoteke, i svaki slog upisati na
	//poziciju koja mu prethodi. Da bi ovo izveli, slog koji se
	//nalazi na poziciji u koju upisujemo slog ispred njega,
	//moramo prethodno sacuvati u nekoj privremenoj promenljivoj,
	//jer posle zelimo taj slog (na isti nacin) upisati na
	//poziciju koja njemu prethodi. U jednom momentu ce se u
	//toj privremenoj promenljivoj pronaci slog koji brisemo.
	//Tad prekidamo algoritam, i taj slog ostaje van datoteke.


	//Moramo proveriti da li uopste ima tog sloga u datoteci;
	//da ne bi sve ispomerali nazad a na kraju se ispostavi
	//da i nemamo sta da izbrisemo;
	if (pronadjiSlog(fajl, evidBroj) == NULL) {
		printf("Nema tog sloga u datoteci.\n");
		return;
	}

	fseek(fajl, 0, SEEK_END);
	BLOK blok;
	SLOG sacuvaniSlog;
	int trebaTruncate = 0;
	int prvi = 1;
	while (1) {
		//Citamo blokove od kraja ka pocetku
		fseek(fajl, -sizeof(BLOK), SEEK_CUR);
		fread(&blok, sizeof(BLOK), 1, fajl);
		fseek(fajl, -sizeof(BLOK), SEEK_CUR);


		//Citam slogove u bloku od poslenjeg kad prvom
		for (int i = FBLOKIRANJA-1; i >= 0; i--) {

			if (i == 0 &&
				strcmp(blok.slogovi[i].evidBroj, OZNAKA_KRAJA_DATOTEKE) == 0) {
				//Ako je prvi slog u bloku OZNAKA_KRAJA_DATOTEKE,
				//taj blok ce biti prazan kad sve ispomeramo unazad,
				//pa cemo zabeleziti sebi da posle trebamo odseci
				//velicinu jednog bloka sa kraja datoteke.
				trebaTruncate = 1;
			}

			if (prvi) {
				//U ovoj prvoj iteraciji petlje, naisli smo na poslednji slog
				//datoteke (jer citamo s kraja). Taj slog cemo samo sacuvati
				//u promenljivoj "sacuvaniSlog", i preskociti ga.
				//Vec u narednoj iteraciji, taj slog mozemo upisati na mesto
				//koje njemu prethodi.
				memcpy(&sacuvaniSlog, &blok.slogovi[i], sizeof(SLOG));
				prvi = 0;
				continue;
			}

			//U prvoj iteraciji petlje smo samo pokupili poslenji slog u fajlu,
			//tako da je ovo kod koji se izvsava od 2 iteracije (pa nadalje).
			//U promenljivoj "sacuvaniSlog" nam se nalazi slog koji treba da upisemo
			//na tekucu poziciju. Pre nego sto to uradimo, prebacicemo taj slog
			//u promenljivu "sadCeBitiUpisan", da bi u promenljivu "sacuvaniSlog"
			//mogli upisati slog sa tekuce pozicije (jer ce u sledecoj iteraciji
			//taj slog biti upisivan na isti nacin).
			SLOG sadCeBitiUpisan;
			memcpy(&sadCeBitiUpisan, &sacuvaniSlog, sizeof(SLOG));
			memcpy(&sacuvaniSlog, &blok.slogovi[i], sizeof(SLOG));
			memcpy(&blok.slogovi[i], &sadCeBitiUpisan, sizeof(SLOG));

			//Eventualno, dolazimo u situaciju da nam se u "sacuvaniSlog"
			//nalazi slog koji brisemo. Ovde mozemo prekinuti algoritam,
			//i taj slog nece biti upisan u datoteku (tehnicki - izbrisan
			//je iz datoteke).
			if (strcmp(sacuvaniSlog.evidBroj, evidBroj) == 0) {

				//Ovaj blok sad mozemo vratiti u fajl (fseek je vec gde treba)
				fwrite(&blok, sizeof(BLOK), 1, fajl);
				//(nema potrebe da vracamo fseek jer ubrzo izlazimo iz f-je)

				//Jos treba odseci kraj fajla, ako imamo prazan blok na kraju.
				if (trebaTruncate) {
					printf("Skracujem fajl...\n");
					fseek(fajl, -sizeof(BLOK), SEEK_END);
					long bytesToKeep = ftell(fajl);
					ftruncate(fileno(fajl), bytesToKeep);
					fflush(fajl); //(da bi se odmah prihvatio truncate)
				}

				printf("Slog je fizicki izbrisan.\n");
				return;
			}

			//Nakon obrade svakog PRVOG sloga u bloku, treba taj
			//blok vratiti u datoteku (opet, krecemo se od poslednjeg
			//ka prvom slogu)
			if (i == 0) {
				//(vec smo gde treba; ne moramo namestati fseek)
				fwrite(&blok, sizeof(BLOK), 1, fajl);
				fseek(fajl, -sizeof(BLOK), SEEK_CUR);
			}
		}


	}

}
