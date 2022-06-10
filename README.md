# Horský stánek

### Popis problému
Stánek, evidence tržby. Placení a výběr produktů.

### Popis řešení
Vstupem programu Horský stánek budou tři soubory. Dva soubory pro majitele stánku. V prním budou vypsány produkty a ceny. V druhém počty (celková cena produktů, kolik peněz majitel získal). Soubor pro zákazníka bude obsahovat produkty a jejich množství a jestli to je pití anebo jídlo. Program začne výběrem zákazník nebo majitel. Při výběru zákazník bude menu, u kterého si budeme moci zobrazit produkty, co si chceme koupit, zároveň jakou částku za to zaplatíme (částka nebude stanovena produktem, ale zákazník zaplatí kolik bude chtít). Obrázek mapy, kde jsou další Horské stánky. 

### Funkční specifikace
Kdo s aplikací pracuje?
- Majitel
  - zobrazit názvy produktů a jejich počet, seřazené od nejmenšího počtu po největší
  - zobrazit co zákazníci nakoupili, kolik za to zaplatili, kolik za stejné množství produktů zaplatil majitel a jestli vydělává nebo prodělává
  - zobrazit názvy produktů a jejich ceny
- Zákazník
  - zobrazit názvy produktů a jejich počet, první jsou vypsány pití a pak jídlo
  - vybrat produkty, které si chce majitel koupit
  - zaplatit
  - ukázat mapu, kde se nacházejí další stánky

### Popis struktury vstupních a výstupních souborů
Vstupní soubor majitel1 obsahuje názvy produktů (name, String) a cenu jendotlivých produktů (priceOneProduct, int). Soubor zakaznik obsahuje množství produktů (amount, int), názvy produktů (name, String) a jestli to je jídlo nebo pití (what, String). Výstupní soubor obsahuje počy kusů produktů (amount, int) a jejich názvy (name, String). Udáje u všech textovýxh souborů jsou odděleny mezerou. Název produktu musí být napsán s velkým počátečním písmenem.

BINÁRNÍ SOUBOR 

4B(int) price (celková cena kolik stály produkty)  
4B(int) income (kolik zákazníci zaplatili) 

### Class diagram
![Horsky_stanek drawio](https://user-images.githubusercontent.com/100836132/171803006-1671af07-66ca-453d-8e67-71a8e7be34b8.png)


## TESTOVÁNÍ
![Snímek obrazovky 2022-06-03 095542](https://user-images.githubusercontent.com/100836132/171812876-a12197ee-4e2c-4fa5-8b92-e4b2974266c9.png)


## POPIS FUNGOVÁNÍ EXTERNÍ KNIHOVNY
![Snímek obrazovky 2022-06-03 091121](https://user-images.githubusercontent.com/100836132/171805600-c9ee2cd0-e0a2-461e-978d-cc93500273d1.png)

Obrázek převedeme na Component pomocí ImageIcon a JLabel (310). Jpanel ukládá a organizuje Components (311). Abychom mohli zobrazit obrázek, musíme použít JFrame (313). Nastavíme velikost (314) a viditelnost (316).
