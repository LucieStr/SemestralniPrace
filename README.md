# Horský stánek

### Popis problému
Stánek, evidence tržby. Placení a výběr produktů.

### Popis řešení
Vstupem programu Horský stánek budou tři soubory. Dva soubory pro majitele stánku. V prním budou vypsány produkty a ceny. V druhém počty (celková cena produktů, kolik peněz majitel získal). Soubor pro zákazníka bude obsahovat produkty a jejich množství a jestli to je pití anebo jídlo. Program začne výběrem zákazník nebo majitel. Při výběru zákazník bude menu, u kterého si budeme moci zobrazit produkty, co si chceme koupit, zároveň jakou částku za to zaplatíme (částka nebude stanovena produktem, ale zákazník zaplatí kolik bude chtít). Obrázek mapy, kde jsou další Horské stánky. 

### Funkční specifikace
- Majitel
  - Menu
  - Výdělek
  - Ceny produktů 
- Zakazník 
  - Menu
  - Vybrat produkty
  - Zaplatit
  - Mapa

### Popis struktury vstupních a výstupních souborů
Vstupní soubor majitel1 obsahuje cenu jendotlivých produktů (priceOneProduct, int) a názvy produktů (name, String). Soubor zakaznik obsahuje množství produktů (amount, int), názvy produktů (name, String) a jestli to je jídlo nebo pití (what, String). Binární soubor obsahuje celkovou cenu produktů (price, int) a kolik zákazníci zaplatili (income, int). Výstupní soubor obssahuje počy kusů produktů (amount, int) a jejich názvy (name, String).

### Class diagram
![Horsky_stanek drawio](https://user-images.githubusercontent.com/100836132/171803006-1671af07-66ca-453d-8e67-71a8e7be34b8.png)


## TESTOVÁNÍ

## POPIS FUNGOVÁNÍ EXTERNÍ KNIHOVNY
![Snímek obrazovky 2022-06-03 091121](https://user-images.githubusercontent.com/100836132/171805600-c9ee2cd0-e0a2-461e-978d-cc93500273d1.png)
Obrázek převedeme na Component pomocí ImageIcon a JLabel (310). Jpanel ukládá a organizuje Components (311). Abychom mohli zobrazit obrázek, musíme použít JFrame (313). Nastavíme velikost (314) a viditelnost (316).
