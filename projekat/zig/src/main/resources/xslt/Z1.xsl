<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:z="http://ftn.uns.ac.rs/zig"
    xmlns:zaj="http://www.ftn.uns.ac.rs/zaj"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:str="http://exslt.org/strings"
    extension-element-prefixes="str">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8"/>
                <title>Zahtev za priznavanje ziga </title>

                <style type="text/css">
                    *{
                    margin:0;
                    padding:0;
                    }

                    body { font-family: sans-serif;

                    }
                    p{
                    margin: 5px 5px 0 5px;

                    }
                    table {
                    font-family: serif;
                    border-collapse: collapse;
                    margin: 0px 20px 20px 20px;
                    width: 100%;
                    }
                    th, td {
                    text-align: left;

                    }

                    th {
                    background-color: #4caf50;
                    font-family: sans-serif;
                    color: white;
                    }
                    tr { border: 2px solid black; margin:0; padding:0;}

                    .title{
                    margin: 0;text-align: center; font-weight:bold; font-size: 20px; border-width:10px; border-color:red;
                    }
                    .subtitle{
                    margin:0; font-weight:bold; text-align: center; margin-bottom: 20px;
                    }
                    .tel-info{
                    margin-bottom: 0;
                    margin-top: 0;
                    }
                    .mail-info{
                    margin-bottom: 0;
                    margin-top: 0;
                    }
                    .description{
                    text-align: center;
                    margin:0;
                    }
                    .question{
                    padding: 5px 5px 0 0 ;
                    border:1px;
                    }
                    .question-in{
                    border:1px;
                    }
                    .answer{
                    height:100px;
                    }
                    .table-in{
                    margin: 0 !important;
                    width:100%;
                    }
                    .uputstvo{
                    margin:0;
                    margin-left: 20px !important;
                    margin-bottom:140px;

                    }

                </style>
            </head>
            <body>

                <div>
                    <p class="title">ZAHTEV ZA PRIZNANJE ZIGA</p>
                    <p class="subtitle">Zavodu za intelektualnu svojinu, Kneginje Ljubice 5, 11000 Beograd</p>
                    <p class="description">(popuniti na racunaru)</p>
                </div>
                <table style="border: 2px; margin-bottom:0;">
                    <tr>
                        <td class="question">
                            <p><span style="font-weight:bold;">1. Podnosilac prijave: </span>ime i prezime/poslovno ime, ulica i broj, postanski broj, mesto,
                                drzava prebivalista/sedista:</p>
                        </td>
                    </tr>
                    <tr><td class="question">
                        <p class="answer">
                            <xsl:for-each select="z:Zahtev_za_priznanje_ziga/z:Podnosioc_prijave">
                            	<xsl:variable name="lice" select="@xsi:type"/>
                                <xsl:choose>
                                    <xsl:when test="substring($lice,5)='TFizicko_lice'">
                                        <p>
                                            <xsl:value-of select="zaj:Ime"/> <xsl:text></xsl:text>&#160;
                                            <xsl:value-of select="zaj:Prezime"/> <xsl:text></xsl:text>
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <p>
                                            p<xsl:value-of select="zaj:Naziv"/><xsl:text></xsl:text>
                                        </p>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <p>
                                    <xsl:value-of select="zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
                                    <xsl:value-of select="zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
                                    <xsl:value-of select="zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
                                    <xsl:value-of select="zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                                </p>
                                <p>
                                    <xsl:value-of select="zaj:Adresa/zaj:Drzava"/> <xsl:text></xsl:text>
                                </p>
                            </xsl:for-each>
                        </p>
                    </td></tr>
                    <tr>
                        <td>
                            <table style="margin:0; width: 100%;">
                                <tr>
                                    <td style="border: 1px;">
                                        <p class="tel-info">telefon:
                                        	<xsl:for-each select="z:Zahtev_za_priznanje_ziga/z:Podnosioc_prijave">
                                            	<xsl:value-of select="zaj:Kontakt_podaci/zaj:Telefon"/><xsl:text></xsl:text>&#160;
                                        	</xsl:for-each>
                                        </p>
                                    </td>
                                    <td style="border: 1px;">
                                        <p class="mail-info">e-mail:
	                                        <xsl:for-each select="z:Zahtev_za_priznanje_ziga/z:Podnosioc_prijave">
	                                        	<xsl:value-of select="zaj:Kontakt_podaci/zaj:Email"/><xsl:text></xsl:text>&#160;
	                                        </xsl:for-each>
                                        </p>
                                    </td>
                                    <td style="border: 1px;">
                                        <p class="mail-info">faks:
	                                        <xsl:for-each select="z:Zahtev_za_priznanje_ziga/z:Podnosioc_prijave">
	                                        	<xsl:value-of select="zaj:Kontakt_podaci/zaj:Faks"/><xsl:text></xsl:text>&#160;
	                                        </xsl:for-each>
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="question">
                            <p><span style="font-weight:bold;">2. Punomocnik: </span>ime i prezime/poslovno ime, ulica i broj, postanski broj, mesto, drzava
                                prebivalista/sedista:</p>
                        </td>
                    </tr>
                    <tr><td class="question"><p class="answer">
                    	<xsl:variable name="lice" select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/@xsi:type"/>
                        <xsl:choose>
                            <xsl:when test="substring($lice,5)='TFizicko_lice'">
                                <p>
                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Ime"/> <xsl:text></xsl:text>&#160;
                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Prezime"/> <xsl:text></xsl:text>
                                </p>
                            </xsl:when>
                            <xsl:otherwise>
                                <p>
                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Naziv"/><xsl:text></xsl:text>
                                </p>
                            </xsl:otherwise>
                        </xsl:choose>
                        <p>
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                        </p>
                        <p>
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Adresa/zaj:Drzava"/> <xsl:text></xsl:text>
                        </p>
                    </p></td></tr>
                    <tr>
                        <td>
                            <table style="margin:0; width: 100%;">
                                <tr>
                                    <td style="border: 1px;">
                                        <p class="tel-info">telefon:<xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Kontakt_podaci/zaj:Telefon"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                    <td style="border: 1px;">
                                        <p class="mail-info">e-mail:<xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Kontakt_podaci/zaj:Email"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                    <td style="border: 1px;">
                                        <p class="mail-info">faks:<xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Punomocnik/zaj:Kontakt_podaci/zaj:Faks"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="question">
                            <p><span style="font-weight:bold;">3. Podaci o zajednickom predstavniku ako postoji vise podnosilaca prijave: </span></p>
                        </td>
                    </tr>
                    <tr><td class="question"><p class="answer" style="padding-bottom:15px;">
                        <p>
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                        </p>
                        </p>
                    </td></tr>
                    <tr>
                        <td>
                            <table style="margin:0; width: 100%;">
                                <tr>
                                    <td style="border: 1px;">
                                        <p class="tel-info">telefon:<xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Kontakt_podaci/zaj:Telefon"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                    <td style="border: 1px;">
                                        <p class="mail-info">e-mail:<xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Kontakt_podaci/zaj:Email"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                    <td style="border: 1px;">
                                        <p class="mail-info">faks:<xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zajednicki_predstavnik/zaj:Kontakt_podaci/zaj:Faks"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style="margin:0; padding:0;">
                        <td style="margin:0; padding:0;">
                            <table class="table-in">
                                <tr style="margin:0; padding:0;">
                                    <td style="width:70%;">
                                        <table class="table-in">
                                            <tr style="margin:0; padding:0;"><td style="border: 1px;">
                                                <p><span style="font-weight:bold;">4. Prijava se podnosi za (upisati X):</span></p>
                                            </td></tr>
                                            <tr style="margin:0; padding:0;">
                                                <td class="question-in" style="border: 1px;">
                                                    <table class="table-in" style="border: 1px;">
                                                        <tr style="margin:0; padding:0;">
                                                            <td style="border: 1px; width:10%">
                                                                <p><span style="font-weight:bold;">a)</span></p>
                                                            </td>
                                                            <td style="border: 1px; width:70%;">
                                                                <table class="table-in">
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>individualni zig</p></td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>kolektivni zig</p></td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>zig garancije</p></td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                            <td style="width:20%">
                                                                <table class="table-in">
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_korisnika = 'individualni zig'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_korisnika = 'kolektivni zig'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_korisnika = 'zig garancije'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr style="margin:0; padding:0;">
                                                <td style="margin:0; padding:0;">
                                                    <table class="table-in" style="border: 1px;">
                                                        <tr style="margin:0; padding:0;">
                                                            <td style="border: 1px; width:10%">
                                                                <p><span style="font-weight:bold;">b)</span></p>
                                                            </td>
                                                            <td style="border: 1px; width:70%">
                                                                <table class="table-in">
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>verbalni znak (znak u reci)</p></td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>graficki znak; boju, kombinaciju boja</p></td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>kombinovani znak</p></td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>trodimenzionalni znak</p></td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question"><p>drugu vrstu znaka (navesti koju)</p></td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                            <td style="width:20%">
                                                                <table class="table-in">
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'verbalni zig'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'graficki'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'kombinovani'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'trodimenzionalni'">
                                                                                    <p style="text-align:center;">x</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p>&#160;</p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="margin:0; padding:0;">
                                                                        <td class="question">
                                                                            <xsl:choose>
                                                                                <xsl:when test="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'trodimenzionalni' or z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'kombinovani' or z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'graficki' or z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda = 'verbalni zig'">
                                                                                    <p>&#160;</p>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <p style="text-align:center;"><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Vrsta_ziga_na_osnovu_izgleda"/> <xsl:text></xsl:text></p>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr style="margin:0; padding:0;"><td class="question" style=" margin:0; padding:0; border: 1px; padding:5px 5px 20px 5px;">
                                                <p><span style="font-weight:bold;">5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:</span>
                                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Podaci_o_boji_znaka"/> <xsl:text></xsl:text>
                                                </p>
                                            </td></tr>
                                            <tr style="margin:0; padding:0;"><td class="question" style=" margin:0; padding:0; border: 1px; padding:5px 5px 15px 5px;">
                                                <p><span style="font-weight:bold;">6. Transliteracija znaka*:</span>
                                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Transliteracija_znaka"/> <xsl:text></xsl:text></p>
                                            </td></tr>
                                            <tr style="margin:0; padding:0;"><td class="question" style="margin:0; padding:0; border: 1px; padding:5px 5px 15px 5px;">
                                                <p><span style="font-weight:bold;">7. Prevod znaka*:</span>
                                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Prevod_znaka"/> <xsl:text></xsl:text></p>
                                            </td></tr>
                                            <tr style="margin:0; padding:0;"><td class="question" style="margin:0; padding:0; border: 1px; padding:5px 5px 20px 5px;">
                                                <p><span style="font-weight:bold;">8. Opis znaka:</span>
                                                    <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Opis_znaka"/> <xsl:text></xsl:text></p>
                                            </td></tr>
                                        </table>
                                    </td>
                                    <td style="width:50%;">
                                        <table class="table-in" style="border:1px">
                                            <tr style="margin:0; padding:0;">
                                                <td style="border:1px">
                                                    <p><span style="font-weight:bold;">v) izgled znaka:</span></p>
                                                </td>
                                            </tr>
                                            <tr style="margin:0; padding:0;">
                                                <td><p style="margin-bottom: 350px;">
                                                 <xsl:variable name="img" select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Izgled_znaka"/>
                                                 <img alt="Izgled znaka" src="{$img}" />
                                                </p></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style="margin:0; padding:0;">
                        <td class="question">
                            <p><span style="font-weight:bold;">9. Zaokruziti brojeve klasa robe i usluga prema Nicanskoj klasifikaciji : </span></p>
                        </td>
                    </tr>
                    <tr style="margin:0; padding:0;">
                        <td>
                           <table class="table-in" style="width:100%">
                                <tr>
                                    <xsl:variable name="match" select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Podaci_o_brojevima_klasa_robe_i_usluga"/>
                                    <xsl:for-each select="(//node())[23 >= position()]">
                                        <xsl:variable name="currentNumber" select="position()"/>
                                        <xsl:choose>
                                            <xsl:when test="string($currentNumber) = str:tokenize($match,' ')">
                                                <td style="border:1px; border-bottom:1px; background-color:silver;"><xsl:value-of select="$currentNumber"/></td>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <td style="border:1px; border-bottom:1px;"><xsl:value-of select="$currentNumber"/></td>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:variable name="match" select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Podaci_o_brojevima_klasa_robe_i_usluga"/>
                                    <xsl:for-each select="(//node())[22 >= position()]">
                                        <xsl:variable name="currentNumber" select="position()+23"/>
                                        <xsl:choose>
                                            <xsl:when test="string($currentNumber) = str:tokenize($match,' ')">
                                                <td style="border:1px; border-bottom:1px; background-color:silver;"><xsl:value-of select="$currentNumber"/></td>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <td style="border:1px; border-bottom:1px;"><xsl:value-of select="$currentNumber"/></td>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:for-each>
                                    <td style="border-left:1px;"> </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style="margin:0; padding:0;">
                        <td class="question" style="padding:5px 5px 10px 5px;">
                            <p><span style="font-weight:bold;">10. Zatrazeno pravo prvenstva i osnov: </span>
                                <xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Pravo_prvenstva_i_osnov"/> <xsl:text></xsl:text>
                            </p>
                        </td>
                    </tr>
                </table>
                <p class="uputstvo">*Popuniti samo ako je znak ili element znaka ispisan slovima koja nisu cirilicna ili latinicna</p>

                <table style="border: 2px; margin-bottom:30px;">
                    <tr style="margin:0; padding:0;">
                        <td style="margin:0; padding:0; border-right:1px; width:60%;">
                            <table class="table-in" style="margin:0; padding:0;">
                                <tr>
                                    <td class="question">
                                        <p><span style="font-weight:bold;">11. Placene takse: </span></p>
                                    </td>
                                    <td style="border:1px;">
                                        <p><span style="font-weight:bold;">Dinara </span></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="question" style="padding:20px 5px 5px 5px;">
                                        <p><span style="font-weight:bold;">a) osnovna taksa </span></p>
                                    </td>
                                    <td class="question" style="border:1px;">
                                        <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Placene_takse/z:Osnovna_taksa"/> <xsl:text></xsl:text></p>
                                    </td>
                                </tr>
                                <tr>
                               		<td class="question">
                               			<table class="table-in">
                               				<tr style="margin:0; padding:0;">
			                                    <td style="padding:15px 5px 5px 5px;">
			                                        <p><span style="font-weight:bold;">b) za
			                                            <xsl:variable name="match" select="z:Zahtev_za_priznanje_ziga/z:Zig/z:Podaci_o_brojevima_klasa_robe_i_usluga"/>
			                                            <xsl:value-of select="count(str:tokenize($match,' '))"/>
			                                            klasa </span></p>
			                                    </td>
			                                </tr>
			                                <tr style="margin:0; padding:0;">
			                                    <td style="padding:15px 5px 5px 5px;">
			                                        <p><span style="font-weight:bold;">v) za graficko resenje </span></p>
			                                    </td>
			                                </tr>
                               			</table>
                               		</td>
                               		<td class="question">
                               			<table>
                               				<tr>
                               					<td style="padding:20px 5px 5px 0;">
			                                        <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Placene_takse/z:Za_klasu"/> <xsl:text></xsl:text></p>
			                                    </td>
                               				</tr>
                               				<tr>
                               				<td style="padding:20px 5px 5px 0;">
			                                        <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Placene_takse/z:Graficko_resenje"/> <xsl:text></xsl:text></p>
			                                    </td>
                               				</tr>
                               			</table>
                               		</td>
                                </tr>
                                <tr>
                                    <td style="border:1px; padding:20px 5px 0 0;">
                                        <p><span style="font-weight:bold;">UKUPNO </span></p>
                                    </td>
                                    <td  style="border:1px;">
                                        <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Placene_takse/z:Ukupan_iznos_takse"/> <xsl:text></xsl:text></p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td style=" margin:0; padding:0; width:40%; border:1px;">
                            <p style="margin:0; text-align: center; margin-bottom: 100px; font-weight: bold;">Potpis podnosioca zahteva</p>
                            <p style="margin:0; text-align: center; font-style:italic; font-size: 12px; margin-bottom: 50px;">
                                * Pecat, ukoliko je potreban u skladu sa zakonom</p>
                        </td>
                    </tr>
                </table>

                <table style="border: 2px; ">
                    <tr>
                        <td class="question" style="padding:30px 5px 5px 5px;">
                            <h4 class="title">POPUNJAVA ZAVOD</h4>
                        </td>
                    </tr>
                    <tr  style="margin:0; padding:0;">
                        <td  style="margin:0; padding:0;">
                            <table class="table-in" style="margin:0; padding:0;">
                                <tr style="margin:0; padding:0;">
                                    <td style="width:60%; margin:0; padding:0;">
                                        <table class="table-in" style="margin:0; padding:0;">
                                            <tr>
                                                <td style="padding:5px 5px 15px 5px;">
                                                    <p><span style="font-weight:bold;">Prilozi uz zahtev:</span></p>
                                                </td>
                                            </tr>
                                            <tr stzle="padding:0;">
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Primerak znaka</p>
                                                </td>
                                                <td class="question" style="border:1px; width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Primerak_znaka/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Spisak robe i usluga**</p>
                                                </td>
                                                <td class="question" style="border:1px; width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Spisak_robe_i_usluga/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Punomocje</p>
                                                </td>
                                                <td class="question" style="border:1px; width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Punomocje/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Generalno punomocje ranije prilozeno</p>
                                                </td>
                                                <td class="question" style="border:1px; width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Punomocje_ranije_prilozeno/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Punomocje ce biti naknadno dostavljeno</p>
                                                </td>
                                                <td class="question" style="border:1px; width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Punomocje_naknadno_dostavljeno/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Opsti akt o kolektivnom zigu/zigu garancije</p>
                                                </td>
                                                <td class="question" style="width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Opsti_akt_o_kolektivnom_zigu_garancije/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Dokaz o pravu prvenstva</p>
                                                </td>
                                                <td class="question" style="width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Dokaz_o_pravu_prvenstva/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="question" style="padding:5px 5px 15px 5px; width:70%;">
                                                    <p>Dokaz o uplati takse</p>
                                                </td>
                                                <td class="question" style=" width:30%;">
                                                    <p><xsl:value-of select="z:Zahtev_za_priznanje_ziga/z:Prilozi_uz_zahtev/z:Dokaz_o_uplati_takse/z:Putanja_do_fajla"/> <xsl:text></xsl:text></p>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td style="width:40%; border:1px;">
                                        <p style="text-align: center;">Broj prijave ziga:</p>
                                        <p style="text-align: center; ">
                                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/@broj_prijave_ziga"/><xsl:text></xsl:text>
                                        </p>
                                        <p style="text-align: center; margin-bottom: 15px; font-weight: bold;"> Datum podnosenja: </p>
                                        <p style="text-align: center; font-weight: bold;">
                                            <xsl:value-of select="z:Zahtev_za_priznanje_ziga/@datum_podnosenja_prijave"/><xsl:text></xsl:text>
                                        </p>
                                        <p style="text-align: center; font-weight: bold;"> ________________________</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

                <p style="margin: 50px 20px;">
                    **Uz zaokruzivanje broja klase robe/usluga Nicanske klasifikacije u rubrici 9 dostavlja se i spisak koji sadrzi konkretne nazive robe koju podnosilac prijave proizvodi, odnosno usluga koje pruza. U cilju odredjenja obima zastite koja se stice zigom, spisak treba da sadrzi jasne i precizne nazive robe i usluga. U tu svrhu mogu se koristiti pojmovi iz detaljne Liste roba i usluga ili MGS Manager aplikacije, dostupnih na sajtu Zavoda. Ukoliko se u spisak unose termini iz Liste klasa Nicanske klasifikacije, zastita obuhvata samo tako imenovane, konkretne robe/usluge u njihovom jasnom i nedvosmislenom znacenju.
                </p>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>