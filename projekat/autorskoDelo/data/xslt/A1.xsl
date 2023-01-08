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
                    }
                    
                    body { font-family: sans-serif;
                    
                    }
                    p{
                    margin: 15px 5px 100px 5px;
                    
                    }
                    table {
                    font-family: serif;
                    border-collapse: collapse;
                    margin: 50px 20px 50px 20px;
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
                    
                   
                    
                    .address{
                    text-align: left;
                    margin-bottom: 10px;
                    margin:0;
                    padding:0;
                    }
                    .title{
                    text-align: center;
                    margin: 10px 0 20px 0;
                    padding: 20px 0;
                    }
                    .tel-info{
                    margin-bottom: 0;
                    margin-top: 0;
                    }
                    .mail-info{
                    margin-bottom: 0;
                    margin-top: 0;
                    }
                    .question{
                    padding: 5px;
                    }
                    .prilog{
                    margin: 10px;
                    }
                    
                    .footer{
                    margin-left: 850px;
                    margin-right: 50px;
                    font-size: 18px;
                    }
                    .footer p{
                    margin:10px 0;
                    padding: 10px;
                    }
                    
                    .sifra-prijave{
                    font-weight: bold;
                    }
                    .uputstvo{
                    margin:0;
                    margin-left: 20px; !important
                    }
                    .datum-prjave{
                    margin:0;
                    padding:0;
                    margin:0 0 10px 0;
                    }
                    
                </style>
            </head>
            <body>
                <table style="border: 1px; margin-bottom: 340px;">
                    <thead style="border: 1px">
                        <tr>
                            <td style="padding: 0; margin: 0;">
                                <table style="margin:0 padding:0;">
                                    <tr>
                                        <td>
                                            <h4>ZAVOD ZA INTELEKTUALNU SVOJINU</h4>
                                        </td>
                                        <td>
                                            <h4>OBRAZAC A-1</h4>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 0; margin: 0; padding-bottom: 20px;">
                                <p class="address">Beograd, Kneginje Ljubice 5</p>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 20px 0;">
                                <h4 class="title">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</h4>
                            </td>
                        </tr>     
                    </thead>
                    <tbody>
                        <tr>
                            <td style="border-top: 1px solid black; padding: 10px;">
                                <p>1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizičko lice, odnosno poslovno ime i sedište nosioca autorskog prava ako je podnosilac pravno lice*:</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table style="margin:0; width: 100%;">
                                    <tr>
                                        <td style="border-right: 1px solid black; border-top: 1px solid black; border-bottom: 1px solid black;">
                                            <p class="tel-info">telefon:</p>
                                        </td>
                                        <td style="border-top: 1px solid black; border-bottom: 1px solid black;"> 
                                            <p class="mail-info">e-mail:</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>2) Pseudonim ili znak autora, (ako ga ima):</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    3) Ime, prezime i adresa punomoćnika, ako se prijava podnosi preko punomoćnika:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo može da se identifikuje*:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    6) Podaci o vrsti autorskog dela (književno delo, muzičko delo, likovno delo, računarski program i dr.) *:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    7) Podaci o formi zapisa autorskog dela (štampani tekst, optički disk i slično) *:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    8) Podaci o autoru ako podnosilac prijave iz tačke 1. ovog zahteva nije autor i to: prezime, ime, adresa i državljanstvo autora (grupe autora ili koautora), a ako su u pitanju jedan ili više autora koji nisu živi, imena autora i godine smrti autora a ako je u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora: 
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    10) Način korišćenja autorskog dela ili nameravani način korišćenja autorskog dela:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <table style="margin:0; width: 100%;">
                                    <tr>
                                        <td style="width:50%">
                                            <p>11)</p>
                                        </td>
                                        <td style="width:50%"> 
                                            <hr/>
                                            <p style="font-weight:bold; margin:0; margin-left: 50px;">Podnosilac prijave, nosilac prava </p>
                                            <p style="margin:0;">
                                                (mesto za potpis fizičkog lica, odnosno potpis
                                                zastupnika pravnog lica ili ovlašćenog predstavnika
                                                u pravnom licu)*
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>  
                <table  style="border: 1px; margin-bottom:0px;">
                    <tbody  style="border: 1px">
                        <tr>
                            <td class="question">
                                <p>12) Prilozi koji se podnose uz zahtev:</p>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 20px 0;">
                                <h4 class="title">POPUNJAVA ZAVOD:</h4>
                            </td>
                        </tr>  
                        <tr>
                            <td style="padding: 10px;">
                                <h5 class="prilozi">Prilozi uz prijavu:</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="prilog">
                                     opis autorskog dela (ako je delo podneto na optičkom disku);
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="prilog">
                                     primer autorskog dela (slika, video zapis, audio zapis)
                                </p>
                            </td>
                        </tr>
                        <tr >
                            <td style="margin:0; padding:200px 5px 0 400px;">
                                <table style="border: 1px; margin:10px;">
                                    <tr>
                                        <td style="padding: 10px;"><p style="margin:0;">Broj prijave</p></td>
                                    </tr>
                                    <tr>
                                        <td style=" border-bottom: 1px; padding: 10px;"><p style="margin:0;" class="sifra-prijave">A-</p></td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 5px;"><p class="datum-prijave">Datum podnošenja:</p></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p class="uputstvo">
                    Rubrike u zahtevu A-1 koje su označene sa * moraju da budu obavezno popunjene.
                </p>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>