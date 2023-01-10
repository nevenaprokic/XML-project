<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:a="http://ftn.uns.ac.rs/a1" 
    xmlns:zaj="http://www.ftn.uns.ac.rs/zaj"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
    xmlns:axf="http://www.antennahouse.com/names/XSL/Extensions"
    version="2.0">
    <xsl:output method="xhtml" encoding="UTF-8" indent="yes"/>
    
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
                    margin: 15px 5px 60px 5px;
                    
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
                    .tel-info-td{
                    padding: 5px;  
                    border-collapse: collapse;
                    border-right-style: solid;
                    border-right-width: 1px;
                    border-top-style: solid;
                    border-top-width: 1px;
                    border-bottom-style: solid;
                    border-bottom-width: 1px;
                    }
                    .mail-info{
                    margin-bottom: 0;
                    margin-top: 0;
                    }
                    .mail-info-td{
                    padding: 5px;
                    border-collapse: collapse;
                    border-top-style: solid;
                    border-top-width: 1px;
                    border-bottom-style: solid;
                    border-bottom-width: 1px;
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
                    .sifra-prijave-td{
                    border-collapse: collapse;
                    border-bottom-style: solid;
                    border-bottom-width: 1px;
                    padding: 10px;
                    }
                    .uputstvo{
                    margin:0;
                    margin-left: 20px;
                    }
                    .datum-prjave{
                    margin:0;
                    padding:0;
                    margin:0 0 10px 0;
                    }
                    .podnosilac-td{
                    padding: 15px, 5px, 15px, 5px;  
                    border-collapse: collapse;
                    border-top-style: solid;
                    border-top-width: 1px;
                    }
                </style>
            </head>
            <body>
                <table style="border: 1px solid black; margin-bottom: 340px;">
                    <thead style="border: 1px solid black">
                        <tr>
                            <td style="padding-top: 3px; margin: 0;">
                                <table style="margin:0; padding:0;">
                                    <tr>
                                        <td style="vertical-align:top; horizontal-align: left;">
                                            <h4>ZAVOD ZA INTELEKTUALNU SVOJINU</h4>
                                        </td>
                                        <td style="vertical-align:top; horizontal-align: left;">
                                            <h4>OBRAZAC A-1</h4>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-top: -10px; margin: 0; padding-bottom: 20px;">
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
                            <td class="podnosilac-td">
                                1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizicko lice, odnosno poslovno ime i sedište nosioca autorskog prava ako je podnosilac pravno lice*:
                            </td>
                        </tr>
                        <tr><td>
                        	<p>
                            <xsl:choose>
                               <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor != ''">
                                	<xsl:variable name="podnosilac" select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor"/>
                               		Podnosilac je autor: <br></br><br></br>
                               		Ime i prezime:
                               		<xsl:value-of select="$podnosilac/zaj:Ime"/> <xsl:text></xsl:text>&#160;
                                    <xsl:value-of select="$podnosilac/zaj:Prezime"/> <xsl:text></xsl:text>
                                    <br></br>
                                    Drzavljanstvo:
									<xsl:value-of select="$podnosilac/zaj:Drzavljanstvo"/> <xsl:text></xsl:text>
									<br></br>
									Adresa:
									<xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                                 </xsl:when>
                                 <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik != ''">
                                	<xsl:variable name="podnosilac" select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik"/>
                               		Podnosilac je punomocnik: <br></br><br></br>
                               		Ime i prezime:
                               		<xsl:value-of select="$podnosilac/zaj:Ime"/> <xsl:text></xsl:text>&#160;
                                    <xsl:value-of select="$podnosilac/zaj:Prezime"/> <xsl:text></xsl:text>
                                    <br></br>
                                    Drzavljanstvo:
									<xsl:value-of select="$podnosilac/zaj:Drzavljanstvo"/> <xsl:text></xsl:text>
									<br></br>
									Adresa:
									<xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                                 </xsl:when>
                                 <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Pravno_lice != ''">
                                	<xsl:variable name="podnosilac" select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Pravno_lice"/>
                               		Podnosilac je pravno lice: <br></br><br></br>
                               		Poslovno ime:
                               		<xsl:value-of select="$podnosilac/zaj:Naziv"/> <xsl:text></xsl:text>&#160;
                                    <br></br>
									Sediste:
									<xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
	                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                                 </xsl:when>
                            </xsl:choose>
                            </p>
                    	</td></tr>
                        <tr>
                            <td>
	                            <table style="margin:0; width: 100%;">
                                    <tr>
                                        <td class="tel-info-td">
                                            <p class="tel-info">telefon:
                                            <xsl:choose>
				                               <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor != ''">
				                                	<xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor/zaj:Kontakt_podaci/zaj:Telefon"/>
				                                 </xsl:when>
				                                 <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik != ''">
				                                	<xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik/zaj:Kontakt_podaci/zaj:Telefon"/>
				                                 </xsl:when>
				                                 <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Pravno_lice != ''">
				                                	<xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Pravno_lice/zaj:Kontakt_podaci/zaj:Telefon"/>
				                                 </xsl:when>
			                               	 </xsl:choose>
                                            </p>
                                        </td>
                                        <td class="mail-info-td"> 
                                            <p class="mail-info">e-mail:
                                            <xsl:choose>
				                               <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor != ''">
				                                	<xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor/zaj:Kontakt_podaci/zaj:Email"/>
				                                 </xsl:when>
				                                 <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik != ''">
				                                	<xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik/zaj:Kontakt_podaci/zaj:Email"/>
				                                 </xsl:when>
				                                 <xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Pravno_lice != ''">
				                                	<xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Pravno_lice/zaj:Kontakt_podaci/zaj:Email"/>
				                                 </xsl:when>
			                               	 </xsl:choose>
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                               
                                
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>2) Pseudonim ili znak autora, (ako ga ima):
                                <br></br>
                                <br></br>
                                <xsl:for-each select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Autori/a:Autor">
	                                <xsl:if test="@primarni = 'true'">
	                                	<xsl:value-of select="a:Pseudonim"/>
	                                </xsl:if>
								</xsl:for-each>
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="question">
                                <p>
                                    3) Ime, prezime i adresa punomoćnika, ako se prijava podnosi preko punomoćnika:
                                    <br></br>
                                    <br></br>
                                    <xsl:choose>
										<xsl:when test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik != ''">
		                                	<xsl:variable name="podnosilac" select="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Punomocnik"/>
		                               		Ime i prezime:
		                               		<xsl:value-of select="$podnosilac/zaj:Ime"/> <xsl:text></xsl:text>&#160;
		                                    <xsl:value-of select="$podnosilac/zaj:Prezime"/> <xsl:text></xsl:text>
		                                    <br></br>
											Adresa:
											<xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
			                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
			                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
			                                <xsl:value-of select="$podnosilac/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
		                                 </xsl:when>
                                 	</xsl:choose>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo može da se identifikuje*:
                                    <br></br>
                                    <br></br>
                                    <xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Identifikator/a:Naslov"/> &#160;
                                    <xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Identifikator/a:Alternativni_naslov"/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:
                                    <br></br>
                                    <br></br>
                                    <xsl:variable name="original" select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Podaci_o_originalu"/>
                                    <xsl:if test="$original != ''">
                                    	Naslov: 
	                                	<xsl:value-of select="$original/a:Identifikator/a:Naslov"/>&#160;
	                                	<xsl:value-of select="$original/a:Identifikator/a:Alternativni_naslovv"/>&#160;
	                                	<br></br>
	                                	Autori:
	                                	<br></br>
	                                	<xsl:for-each select="$original/a:Autori/a:Autor">
			                                <xsl:if test="@primarni = 'true'">(Primarni) Autor:</xsl:if>
			                                <xsl:if test="@primarni != 'true'">Autor:</xsl:if>
			                                <br></br>
			                                Ime i prezime: 
			                                <xsl:value-of select="zaj:Ime"/>&#160;
			                                <xsl:value-of select="zaj:Prezime"/>&#160;
			                                <xsl:if test="@anonimni = 'true'">Anoniman</xsl:if>

			                                <xsl:if test="zaj:Drzavljanstvo != ''"><br></br>Drzavljanstvo: <xsl:value-of select="zaj:Drzavljanstvo"/></xsl:if>
			                                <xsl:if test="a:Pseudonim != ''"><br></br>Pseudonim: <xsl:value-of select="a:Pseudonim"/></xsl:if>
			                                <xsl:if test="a:Godina_smrti != ''"><br></br>Godina smrti: <xsl:value-of select="a:Godina_smrti"/></xsl:if>
			                                <xsl:if test="zaj:Adresa != ''">
				                                <br></br>
												Adresa:
												<xsl:value-of select="zaj:Adresa/zaj:Ulica"/>&#160;
				                                <xsl:value-of select="zaj:Adresa/zaj:Broj"/>,
				                                <xsl:value-of select="zaj:Adresa/zaj:Postanski_broj"/>&#160;
				                                <xsl:value-of select="zaj:Adresa/zaj:Grad"/>
				                            </xsl:if>
										</xsl:for-each>
	                                </xsl:if>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    6) Podaci o vrsti autorskog dela (književno delo, muzicko delo, likovno delo, racunarski program i dr.) *:
                                    <br></br>
                                    <br></br>
                                    <xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/@vrsta"/>&#160;
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    7) Podaci o formi zapisa autorskog dela (štampani tekst, opticki disk i slicno) *:
                                    <br></br>
                                    <br></br>
                                    <xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/@forma_zapisa"/>&#160;
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    8) Podaci o autoru ako podnosilac prijave iz tacke 1. ovog zahteva nije autor i to: prezime, ime, adresa i državljanstvo autora (grupe autora ili koautora), a ako su u pitanju jedan ili više autora koji nisu živi, imena autora i godine smrti autora a ako je u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora:
                                    
									<xsl:if test="a:Zahtev_za_autorsko_delo/a:Podnosilac/a:Autor = ''">
	                                	<xsl:for-each select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Autori/a:Autor">
	                                		<br></br>
	                                		<br></br>
		                               		<xsl:if test="@primarni = 'true'">(Primarni) Autor:</xsl:if>
			                                <xsl:if test="@primarni != 'true'">Autor:</xsl:if>
			                                <br></br>
			                                <xsl:choose>
												<xsl:when test="@anonimni = 'true'">
													Anoniman
												</xsl:when>
												<xsl:otherwise>
													Ime i prezime:
				                               		<xsl:value-of select="zaj:Ime"/> <xsl:text>&#160;</xsl:text>
				                                    <xsl:value-of select="zaj:Prezime"/>
												</xsl:otherwise>
											</xsl:choose>
											<br></br>
											Adresa:
											<xsl:value-of select="zaj:Adresa/zaj:Ulica"/> <xsl:text>&#160;</xsl:text> 
			                                <xsl:value-of select="zaj:Adresa/zaj:Broj"/> ,
			                                <xsl:value-of select="zaj:Adresa/zaj:Postanski_broj"/> <xsl:text> &#160;</xsl:text>
			                                <xsl:value-of select="zaj:Adresa/zaj:Grad"/>

											<xsl:if test="zaj:Drzavljanstvo != ''"><br></br>Drzavljanstvo: <xsl:value-of select="zaj:Drzavljanstvo"/></xsl:if>
			
			                                <xsl:if test="a:Pseudonim != ''"><br></br>Pseudonim: <xsl:value-of select="a:Pseudonim"/></xsl:if>
			                                <xsl:if test="a:Godina_smrti != ''"><br></br>Godina smrti: <xsl:value-of select="a:Godina_smrti"/></xsl:if>
			                                
										</xsl:for-each>
									</xsl:if>
                                     
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:
                                    <br></br>
                                    <br></br>
                                    <xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Radni_odnos"/><xsl:text></xsl:text>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="question">
                                <p>
                                    10) Nacin korišćenja autorskog dela ili nameravani nacin korišćenja autorskog dela:
                                    <br></br>
                                    <br></br>
                                    <xsl:value-of select="a:Zahtev_za_autorsko_delo/a:Autorsko_delo/a:Nacin_koriscenja"/><xsl:text></xsl:text>
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
                                                (mesto za potpis fizickog lica, odnosno potpis
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
                <table  style="border: 1px solid black; margin-bottom:0px;">
                    <tbody  style="border: 1px solid black">
                        <tr>
                            <td class="question">
                                <p>12) Prilozi koji se podnose uz zahtev:
                                <br></br>
                                <br></br>
                                <xsl:if test="a:Zahtev_za_autorsko_delo/a:Prilozi/a:Prisutan_opis = 'true'">
                                	Prilozen je opis autorskog dela
                                </xsl:if>
                                <xsl:if test="a:Zahtev_za_autorsko_delo/a:Prilozi/a:Prisutan_primer = 'true'">
                                	Prilozen je primer autorskog dela
                                </xsl:if>
                                </p>
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
                                
                                    opis autorskog dela (ako je delo podneto na optickom disku)
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
                                <table style="border: 1px solid black; margin:10px;">
                                    <tr>
                                        <td style="padding: 10px;"><p style="margin:0;">Broj prijave</p></td>
                                    </tr>
                                    <tr>
                                        <td class="sifra-prijave-td">
	                                        <p style="margin:0;" class="sifra-prijave">                                            
	                                        <xsl:value-of select="a:Zahtev_za_autorsko_delo/@broj_prijave"/>
											</p>
										</td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 5px;">
                                        	<p class="datum-prijave">Datum podnošenja:
                                        		<br></br>
                                        		<p class="sifra-prijave">
                                        			<xsl:value-of select="a:Zahtev_za_autorsko_delo/@datum_podnosenja"/>
                                       			</p>
                                        	</p>
                                       	</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p class="uputstvo">
                    Rubrike u zahtevu A-1 koje su oznacene sa * moraju da budu obavezno popunjene.
                </p>
            </body>
        </html>
    </xsl:template>
    
    
</xsl:stylesheet>