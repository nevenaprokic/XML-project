<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://ftn.uns.ac.rs/a1" 
    xmlns:zaj="http://www.ftn.uns.ac.rs/zaj"
    version="2.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8"/>
                <title>Zahtev za unošenje u evidenciju i deponovanje autorskih dela</title>
                
                <style type="text/css">
                    
                    
                    *{
                    margin:0;
                    padding:0;
                    font-size:14px;
                    }
                    
                    body { font-family: sans-serif;
                    
                    }
                    
                    table {
                    font-family: serif;
                    border-collapse: collapse;
                    margin: 50px 20px 0px 20px;
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
                    tr { border: 2px solid black; }
                    
                    .uputstvo{
                    margin:0;
                    margin-left: 20px; !important
                    }
                    .title-table{
                    text-align:center;
                    }
                    .text{
                    padding: 10px 0 20px 5px;
                    }
                    .line{
                    border-top:1px;
                    border-bottom:1px;
                    }
                    .ustanova{
                    margin: 0 0 0 20px;
                    }
                    .title{
                    margin: 0;text-align: center; font-weight:bold; font-size: 20px;
                    }
                    .row-title{
                    margin: 0; font-weight:bold; font-size: 14px;
                    }
                    .description{
                    text-align: center;
                    margin:0;
                    margin-top: 50px;
                    }
                    
                    
                </style>
            </head>
            <body>
                <p style="text-align:right; font-size:12px; color:silver;">Obrazac P-1</p>
                <table style="border: 1px; width: 70%">
                    <tr>
                        <td style="padding: 5px; margin: 0;">
                            <p class="title-table">Popunjava Zavod</p>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 0; margin: 0; " class="line">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:90%; padding:5px;">
                                        <p style="padding: 5px">Broj prijave</p>
                                        <p style="padding: 0 0 10px 100px">P</p>
                                    </td>
                                    <td style="width:10%">
                                        <p>(21)</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:40% padding:5px;">
                                        <p class="text">Datum prijema</p>
                                    </td>
                                    <td style="border-left: 1px; width:50% padding:5px;">
                                        <p class="text">Priznati datum podnošenja	(22)</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="line" style="padding:5px;">
                            <p class="text">Pečat i potpis</p>
                        </td>
                    </tr> 
                </table>  
                <div>
                    <p class="ustanova">Republika Srbija</p>
                    <p class="ustanova">Zavod za intelektualnu svojinu</p>
                    <p class="ustanova">Kneginje Ljubice broj 5</p>
                    <p class="ustanova">11000 Beograd</p>
                </div>
                <div>
                    <p class="title">ZAHTEV</p>
                    <p class="title">ZA PRIZNANJE PATENTA</p>
                    <p class="description">(popuniti pisaćom mašinom ili računarom)</p>
                </div>
                <table  style="border: 1px; margin-bottom:160px; margin-top:0px; width:100%">
                    <tbody  style="border: 1px">
                        <tr>
                            <td style="padding: 15px 5px;">
                                <table style="margin:0;">
                                    <tr>
                                        <td style="width:20%">
                                            <p class="row-title">Polje broj I</p>
                                        </td>
                                        <td style="width:75%">
                                            <p class="row-title">NAZIV PRONALASKA</p>
                                        </td>
                                        <td style="width:5%">
                                            <p>(54)</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 5px 5px 20px 5px;">
                                <p>* Naziv pronalaska treba da jasno i sažeto izražava suštinu pronalaska i ne sme da sadrži izmišljene ili komercijalne nazive, žigove, imena, šifre, uobičajene skraćenice za proizvode i sl.</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="line" style="padding: 7px 5px;">
                                <p>Na srpskom jeziku:</p>
                                <p>Na engleskom jeziku: </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="line" style="padding: 10px 5px;">
                                <table style="margin:0;">
                                    <tr>
                                        <td style="width:20%">
                                            <p class="row-title">Polje broj II</p>
                                        </td>
                                        <td style="width:40%">
                                            <p class="row-title">PODNOSILAC PRIJAVE</p>
                                        </td>
                                        <td style="width:40%">
                                            <input type="checkbox"></input>
                                            <p>&#x2610; Podnosilac prijave je i pronalazač </p>
                                        </td>
                                        <td style="width:5%">
                                            <p>(71)</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table style="margin:0;">
                                    <tr>
                                        <td style="width:70%">
                                            <table style="margin:0;">
                                                <tr><td>
                                                    <table style="margin:0;">
                                                        <tr>
                                                            <td style="padding: 5px 5px 130px 5px; width:50%;">
                                                                <p>Ime i prezime / Poslovno ime: <span style="font-size:12px;">(prezime / poslovno ime upisati velikim slovima)</span></p>
                                                            </td>
                                                            <td style="border-left:1px; padding: 5px 5px 130px 5px; width:50%;">
                                                                <p>Ulica i broj, poštanski broj, mesto i država:</p>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td></tr>
                                                <tr><td style="padding: 10px 5px 30px 5px;">
                                                    <table style="margin:0; border-top:1px;">
                                                        <tr>
                                                            <td>
                                                                <p>Državljanstvo: </p>
                                                            </td>
                                                            <td style="font-size: 14px;">
                                                                <p>(popuniti samo za fizička lica)</p>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td></tr>
                                            </table>
                                        </td>
                                        <td style="width:30%">
                                            <table style="margin:0; border-left:1px;">
                                                <tr><td style="border-bottom:1px; padding: 20px 5px 43px 5px;"><p>Broj telefona:</p></td></tr>
                                                <tr><td style="border-bottom:1px; padding: 20px 5px 43px 5px;"><p>Broj faksa:</p></td></tr>
                                                <tr><td style="padding: 20px 5px 43px 5px;"><p>E-pošta:</p></td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p style="text-align:right; font-size:12px; color:silver;">Obrazac P-1, str. 2</p>
                <table style="border: 1px; margin-top:0; margin-bottom:0px;">
                    <tr>
                        <td style="padding: 10px 5px 5px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj III </p>
                                    </td>
                                    <td style="width:40%">
                                        <p class="row-title"> PRONALAZAČ </p>
                                    </td>
                                    <td style="width:5%">
                                        <p>(72)</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-bottom:1px;">
                            <p style="font-size: 12px; margin:0; padding:0; padding-left:5px; padding-bottom:5px;">(ako su svi pronalazači ujedno i podnosioci prijave, polje broj III se ne popunjava)</p>
                            <p style="font-size: 12px; margin:0; padding:0; padding-left:5px;">* Ako svi podnosioci prijave nisu i pronalazači, dostavlja se izjava podnosilaca prijave o osnovu sticanja prava na podnošenje prijave u odnosu na pronalazače koji nisu i podnosioci prijave i u tom slučaju u polje broj III se unose podaci o svim pronalazačim</p>
                            <p style="margin:0; padding:0; margin-right:50px; text-align:right;">Pronalazač ne želi da bude naveden u prijavi</p>
                            <p style="font-size: 12px; margin:0; padding:0; padding-left:5px;  padding-bottom:5px;">(ako pronalazač ne želi da bude naveden u prijavi polje broj III se ne popunjava)</p>
                            <p style="font-size: 12px; margin:0; padding:0; padding-left:5px;">*Ako pronalazač ne želi da bude naveden u prijavi, potrebno je dostaviti potpisanu izjavu pronalazača da ne želi da bude naveden.</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:70%;">
                                        <table style="margin:0;">
                                            <tr><td>
                                                <table style="margin:0;">
                                                    <tr>
                                                        <td style="padding: 5px 5px 70px 5px; width:50%;">
                                                            <p>Ime i prezime / Poslovno ime: <span>(prezime / poslovno ime upisati velikim slovima)</span></p>
                                                        </td>
                                                        <td style="border-left:1px; padding: 5px 5px 70px 5px; width:50%;">
                                                            <p>Ulica i broj, poštanski broj, mesto i država:</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td></tr>
                                        </table>
                                    </td>
                                    <td style="width:30%;">
                                        <table style="margin:0; border-left:1px;">
                                            <tr><td style="border-bottom:1px; padding: 5px 5px 15px 5px;"><p>Broj telefona:</p></td></tr>
                                            <tr><td style="border-bottom:1px; padding: 5px 5px 15px 5px;"><p>Broj faksa:</p></td></tr>
                                            <tr><td style="padding: 5px 5px 15px 5px;"><p>E-pošta:</p></td></tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                
                
                
                
                <table style="border: 1px; margin-top:10px;">
                    <tr>
                        <td style="padding: 10px 5px 5px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj IV  </p>
                                    </td>
                                    <td style="width:37%">
                                        <p class="row-title">PUNOMOĆNIK ZA ZASTUPANJE  </p>
                                    </td>
                                    <td style="width:37%">
                                        <p class="row-title">PUNOMOĆNIK ZA PRIJEM PISMENA  </p>
                                    </td>
                                    <td style="width:5%">
                                        <p>(74)</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">.</p>
                                    </td>
                                    <td style="width:80%">
                                        <p class="row-title">ZAJEDNIČKI PREDSTAVNIK</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-bottom:1px;">
                            <p style="font-size: 12px; margin:0; padding:0 5px;">* Punomoćnik za zastupanje je lice koje po ovlašćenju podnosioca prijave preduzima radnje u upravnom postupku u granicama punomoćja</p>
                            <p style="font-size: 12px; margin:0; padding:0 5px;">* Punomoćnik za prijem pismena je lice koje je podnosilac prijave odredio kao lice kome se  upućuju sva pismena naslovljena na podnosioca</p>
                            <p style="font-size: 12px; margin:0; padding:0 5px; padding-bottom: 15px;">* Zajedniči prestavnik je podnosilac prijave koga su podnosioci prijave, u slučaju da prijavu podnosi više lica, odredili da istupa u postupku pred organom ako podnosioci nisu imenovali zajedničkog punomoćnika za zastupanje</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:70%;">
                                        <table style="margin:0;">
                                            <tr><td>
                                                <table style="margin:0;">
                                                    <tr>
                                                        <td style="padding: 5px 5px 40px 5px; width:50%;">
                                                            <p>Ime i prezime / Poslovno ime: <span>(prezime / poslovno ime upisati velikim slovima)</span></p>
                                                        </td>
                                                        <td style="border-left:1px; padding: 5px 5px 40px 5px; width:50%;">
                                                            <p>Ulica i broj, poštanski broj, mesto i država:</p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td></tr>
                                        </table>
                                    </td>
                                    <td style="width:30%;">
                                        <table style="margin:0; border-left:1px;">
                                            <tr><td style="border-bottom:1px; padding: 5px 5px 20px 5px;"><p>Broj telefona:</p></td></tr>
                                            <tr><td style="padding: 5px 5px 20px 5px;"><p>E-pošta:</p></td></tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-top:1px; padding: 15px 5px 5px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj V </p>
                                    </td>
                                    <td style="width:80%">
                                        <p class="row-title">ADRESA ZA DOSTAVLJANJE </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-bottom:1px;  padding: 0px 5px 20px 5px;">
                            <p>(ovo polje se popunjava ako podnosilac prijave, zajednički predstavnik ili punomoćnik želi da se dostavljanje podnesaka vrši na drugoj adresi od njegove navedene adrese)</p>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 10px 5px 0 5px;">
                            <p>Ulica i broj, poštanski broj i mesto:</p>
                        </td>
                    </tr>
                    <tr>
                        <td class="line" style="padding: 10px 5px 15px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj VI </p>
                                    </td>
                                    <td style="width:80%">
                                        <p class="row-title">NAČIN DOSTAVLJANJA </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 5px 5px 5px 5px; margin:0;">
                            <p>cek Podnosilac prijave je saglasan da Zavod vrši dostavljanje pismena isključivo elektronskim putem u formi elektronskog dokumenta (u ovom slučaju neophodna je registracija na portalu „eUprave”)</p>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 0px 5px 5px 5px; margin:0;">
                            <p>cek Podnosilac prijave je saglasan da Zavod vrši dostavljanje pismena u papirnoj formi </p>
                        </td>
                    </tr>
                    <tr>
                        <td class="line" style="padding: 10px 5px 5px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj VII </p>
                                    </td>
                                    <td style="width:37%">
                                        <xsl:element name="input">
                                            <xsl:attribute name="type">checkbox</xsl:attribute>
                                        </xsl:element>
                                        <p class="row-title">DOPUNSKA PRIJAVA  </p>
                                    </td>
                                    <td style="width:37%">
                                        <p class="row-title">IZDVOJENA PRIJAVA  </p>
                                    </td>
                                    <td style="width:6%">
                                        <p class="row-title">(61)/(62)  </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 10px 5px 0 5px;">
                            <p>Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta:   </p>
                        </td>
                    </tr>
                    <tr>
                        <td class="line" style="padding: 10px 5px 0 5px;">
                            <p>Datum podnošenja prvobitne prijave / osnovne prijave:   </p>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 10px 5px 10px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj VIII </p>
                                    </td>
                                    <td style="width:75%">
                                        <p class="row-title">ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ RANIJIH PRIJAVA:  </p>
                                    </td>
                                    <td style="width:5%">
                                        <p class="row-title">(30)  </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <p style="text-align:right; font-size:12px; color:silver;">Obrazac P-1</p>
                <table  style="border: 1px; margin-top:0;">
                    <thead>
                        <tr>
                            <td style="width:4%; text-align:center; border-left:1px; padding:5px;">N</td>
                            <td style="width:32%; text-align:center; border-left:1px;  padding:5px;">Datum podnošenja ranije prijave</td>
                            <td style="width:32%; text-align:center; border-left:1px;  padding:5px;">Broj ranije prijave</td>
                            <td style="width:32%; text-align:center; border-left:1px;  padding:5px;">Dvoslovna oznaka države, regionalne ili međunarodne organizacije</td>
                        </tr>
                        <tr>
                            <td style="width:4%; border-left:1px; border-top:1px;"><xsl:value-of select="position()"/></td>
                            <td style="width:32%; border-left:1px; border-top:1px;">x</td>
                            <td style="width:32%; border-left:1px; border-top:1px;">x</td>
                            <td style="width:32%; border-left:1px; border-top:1px;">x</td>
                        </tr>
                    </thead>
                </table>
                
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>