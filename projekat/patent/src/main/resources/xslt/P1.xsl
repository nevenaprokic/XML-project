<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet   
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:p="http://www.ftn.uns.ac.rs/p1"
    xmlns:zaj="http://www.ftn.uns.ac.rs/zaj"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:str="http://exslt.org/strings"
    extension-element-prefixes="str"
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
	              	border-collapse: collapse;
					border-top-style: solid;
					border-top-width: 1px;
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
            	<xsl:variable name="ujedno_pronalazac" select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:pronalazac"/>
                <p style="text-align:right; font-size:12px; color:silver;">Obrazac P-1</p>
                <table style="border: 1px; width: 70%">
                    <tr>
                        <td style="padding: 5px; margin: 0;">
                            <p class="title-table">Popunjava Zavod</p>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 0; margin: 0;" class="line">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:90%; padding:5px;">
                                        <p style="padding: 5px">Broj prijave</p>
                                        <p style="padding: 0 0 10px 100px">
                                        	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/@broj_prijave"/> <xsl:text></xsl:text> 
                                        </p>
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
                                    <td style="width:40% padding:5px; border-collapse: collapse;
													border-top-style: solid;
													border-top-width: 1px;">
                                        <p class="text">Datum prijema</p>
                                         <p style="text-align: center; font-weight: bold;">
                                            <xsl:value-of select="p:Zahtev_za_priznanje_patenta/@datum_prijema_prijave"/><xsl:text></xsl:text>
                                        </p>
                                    </td>
                                    <td style="border-collapse: collapse;
												border-top-style: solid;
												border-top-width: 1px; 
												border-left-style: solid;
												border-left-width: 1px; 
												width:50% padding:5px;">
                                        <p class="text">Priznati datum podnošenja	(22)</p>
                                         <p style="text-align: center; font-weight: bold;">
                                            <xsl:value-of select="p:Zahtev_za_priznanje_patenta/@priznati_datum_podnosenja"/><xsl:text></xsl:text>
                                        </p>
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
                <table  style="border: 1px; margin-bottom:10px; margin-top:0px; width:100%">
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
                            <td class="line" style="padding: 7px 5px; ">
                                <p>Na srpskom jeziku:
                                	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazak/p:Naziv_na_srpskom"/><xsl:text></xsl:text>
                                </p>
                                <p>Na engleskom jeziku: 
                                	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazak/p:Naziv_na_engleskom"/><xsl:text></xsl:text>
                                </p>
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
                                        <xsl:if test="$ujedno_pronalazac='true'">
	                                        <td style="width:40%">
	                                            <p>&#x2610; Podnosilac prijave je i pronalazač </p>
	                                        </td>
                                        </xsl:if>
                                        <td style="width:5%">
                                            <p>(71)</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table style="margin:0; border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px;
														border-bottom-style: solid;
														border-bottom-width: 1px;">
                                    <tr>
                                        <td style="width:70%">
                                            <table style="margin:0;">
                                                <tr><td>
                                                    <table style="margin:0;">
                                                        <tr>
                                                            <td style="padding: 5px 5px 130px 5px; width:50%; 
		                                                            ">
                                                                <p>Ime i prezime / Poslovno ime: 
                                                                	<span style="font-size:12px;">(prezime / poslovno ime upisati velikim slovima)</span>
                                                                </p>
                                                                <p>
                                                                	<xsl:variable name="lice" select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/@xsi:type"/>
											                        <xsl:choose>
											                            <xsl:when test="substring($lice,5)='TFizicko_lice'">
											                                <p>
											                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Ime"/> <xsl:text></xsl:text>&#160;
											                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Prezime"/> <xsl:text></xsl:text>
											                                </p>
											                            </xsl:when>
											                            <xsl:otherwise>
											                                <p>
											                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Naziv"/><xsl:text></xsl:text> 
											                                </p>
											                            </xsl:otherwise>
								                        			</xsl:choose>
                                                                </p>
                                                            </td>
                                                            <td style="border-collapse: collapse;
																		border-left-style: solid;
																		border-left-width: 1px; 
                                                            		   padding: 5px 5px 130px 5px; width:50%;">
                                                                <p>Ulica i broj, poštanski broj, mesto i država:</p>
                                                                <P>
                                                               			<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
									                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
									                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
									                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                                
                                                                </P>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td></tr>
                                                <tr><td style="padding: 5px 5px 5px 5px;">
                                                    <table style="margin:0; border-top:1px;">
                                                        <tr>
                                                            <td>
                                                                <p>Državljanstvo: 
                                                                	<xsl:variable name="lice" select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/@xsi:type"/>
                                                                	<xsl:if test="substring($lice,5)='TFizicko_lice'">
                                                                		<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/p:Drzavljanstvo"/> <xsl:text></xsl:text> &#160;
                                                                	</xsl:if>
                                                                </p>
                                                            </td>
                                                            <td style="font-size: 14px;">
                                                                <p>(popuniti samo za fizička lica)</p>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td></tr>
                                            </table>
                                        </td>
                                        <td style="width:30%" >
                                            <table style="margin:0; border-left:1px; 
                                            			border-collapse: collapse;
														border-left-style: solid;
														border-left-width: 1px;">
                                                <tr><td style="padding: 5px 5px 15px 5px;"><p>Broj telefona:</p>
														<p>
															<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Kontakt_podaci/zaj:Telefon"/> <xsl:text></xsl:text> &#160;
														</p>
														</td></tr>
                                                <tr><td style="border-collapse: collapse;
													border-top-style: solid;
													border-top-width: 1px; 
													padding: 5px 5px 15px 5px;"><p>Broj faksa:</p>
														<p>
															<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Kontakt_podaci/zaj:Faks"/> <xsl:text></xsl:text> &#160;
														</p>
												</td></tr>
	                                            <tr><td style="border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px; 
														border-left-style: solid;
														border-left-width: 1px;
														padding: 5px 5px 15px 5px;"><p>E-pošta:</p>
														<p>
															<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Podnosilac_zahteva/p:Lice/zaj:Kontakt_podaci/zaj:Email"/> <xsl:text></xsl:text> &#160;
														</p>
												</td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
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
                            <xsl:variable name="anoniman" select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/@anoniman"/>
                            <xsl:if test="$anoniman='true'">
                            	<p style="margin:0; padding:0; margin-right:50px; text-align:right; font-weight:bold;">Pronalazač ne želi da bude naveden u prijavi</p>
                            </xsl:if>
                            <p style="font-size: 12px; margin:0; padding:0; padding-left:5px;  padding-bottom:5px;">(ako pronalazač ne želi da bude naveden u prijavi polje broj III se ne popunjava)</p>
                            <p style="font-size: 12px; margin:0; padding:0; padding-left:5px;">*Ako pronalazač ne želi da bude naveden u prijavi, potrebno je dostaviti potpisanu izjavu pronalazača da ne želi da bude naveden.</p>
                        </td>
                    </tr>
                    <tr>
                            <td>
                                <table style="margin:0; border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px;
														border-bottom-style: solid;
														border-bottom-width: 1px;">
                                    <tr>
                                    	<xsl:variable name="anoniman" select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/@anoniman"/>
                                        <td style="width:70%">
                                            <table style="margin:0;">
                                                <tr><td>
                                                    <table style="margin:0;">
                                                    
                                                        <tr>
                                                            <td style="padding: 5px 5px 130px 5px; width:50%; 
		                                                            ">
                                                                <p>Ime i prezime / Poslovno ime: 
                                                                	<span style="font-size:12px;">(prezime / poslovno ime upisati velikim slovima)</span>
                                                                </p>
                                                                <xsl:if test="$anoniman='false' and $ujedno_pronalazac='false'">
	                                                                <p>
		                                                            	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Ime"/> <xsl:text></xsl:text> &#160;
		                                                            	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Prezime"/> <xsl:text></xsl:text>
	                                                           		 </p>
                                                                </xsl:if>
                                                                
                                                            </td>
                                                            <td style="border-collapse: collapse;
																		border-left-style: solid;
																		border-left-width: 1px; 
                                                            		   padding: 5px 5px 130px 5px; width:50%;">
                                                                <p>Ulica i broj, poštanski broj, mesto i država:</p>
                                                                
                                                                <xsl:if test="$anoniman='false' and $ujedno_pronalazac='false'">
	                                                                <p>
		                                                            	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
		                                                            	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text> &#160;
		                                                            	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
		                                                            	<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text> &#160;
	                                                            	
	                                                            	</p>
                                                            	</xsl:if>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td></tr>
                                                
                                            </table>
                                        </td>
                                        <td style="width:30%" >
                                            <table style="margin:0; border-left:1px; 
                                            			border-collapse: collapse;
														border-left-style: solid;
														border-left-width: 1px;">
                                                <tr><td style="padding: 5px 5px 15px 5px;"><p>Broj telefona:</p>
													
                                                        <xsl:if test="$anoniman='false' and $ujedno_pronalazac='false'">
															<p>
																<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Kontakt_podaci/zaj:Telefon"/> <xsl:text></xsl:text> &#160;
															</p>
														</xsl:if>
														</td></tr>
                                                <tr><td style="border-collapse: collapse;
													border-top-style: solid;
													border-top-width: 1px; 
													padding: 5px 5px 15px 5px;"><p>Broj faksa:</p>
														
														
                                                         <xsl:if test="$anoniman='false' and $ujedno_pronalazac='false'">
															<p>
																<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Kontakt_podaci/zaj:Faks"/> <xsl:text></xsl:text> &#160;
															</p>
														</xsl:if>
												</td></tr>
	                                            <tr><td style="border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px; 
														border-left-style: solid;
														border-left-width: 1px;
														padding: 5px 5px 15px 5px;"><p>E-pošta:</p>
														
                                                        <xsl:if test="$anoniman='false' and $ujedno_pronalazac='false'">
															<p>
																<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Pronalazac/zaj:Kontakt_podaci/zaj:Email"/> <xsl:text></xsl:text> &#160;
															</p>
														</xsl:if>
												</td></tr>
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
                                    <td style="width:15%">
                                        <p class="row-title">Polje broj IV  </p>
                                    </td>
                                    <td style="width:20%; margin-left:20px;">
                                        <p class="row-title">PUNOMOCNIK </p>
                                    </td>
                                    <xsl:variable name="za_zastupanje" select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:za_zastupanje"/>
                                    <xsl:variable name="za_prijem_pismena" select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:za_prijem_pismena"/>
                                    <xsl:if test="$za_zastupanje='true' and $za_prijem_pismena='false'">
	                                    <td style="width:60%">
	                                        <p class="row-title">PUNOMOĆNIK ZA ZASTUPANJE  </p>
	                                    </td>
	                                </xsl:if>
	                                <xsl:if test="$za_zastupanje='false' and $za_prijem_pismena='true'">
	                                    <td style="width:60%">
	                                        <p class="row-title">PUNOMOĆNIK ZA PRIJEM PISMENA  </p>
	                                    </td>
                                    </xsl:if>
                                    <xsl:if test="$za_zastupanje='true' and $za_prijem_pismena='true'">
	                                    <td style="width:60%">
	                                        <p class="row-title">ZAJEDNIČKI PREDSTAVNIK</p>
	                                    </td>
                                    </xsl:if>
                                    <td style="width:5%">
                                        <p>(74)</p>
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
                                <table style="margin:0; border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px;
														border-bottom-style: solid;
														border-bottom-width: 1px;">
                                    <tr>
                                        <td style="width:70%">
                                            <table style="margin:0;">
                                                <tr><td>
                                                    <table style="margin:0;">
                                                        <tr>
                                                            <td style="padding: 5px 5px 130px 5px; width:50%; 
		                                                            ">
                                                                <p>Ime i prezime / Poslovno ime: 
                                                                	<span style="font-size:12px;">(prezime / poslovno ime upisati velikim slovima)</span>
                                                                </p>
                                                                <p>
                                                                	<xsl:variable name="lice" select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/@xsi:type"/>
											                        <xsl:choose>
											                            <xsl:when test="substring($lice,5)='TFizicko_lice'">
											                                <p>
											                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Ime"/> <xsl:text></xsl:text>&#160;
											                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Prezime"/> <xsl:text></xsl:text>
											                                </p>
											                            </xsl:when>
											                            <xsl:otherwise>
											                                <p>
											                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Naziv"/><xsl:text></xsl:text> 
											                                </p>
											                            </xsl:otherwise>
								                        			</xsl:choose>
                                                                </p>
                                                            </td>
                                                            <td style="border-collapse: collapse;
																		border-left-style: solid;
																		border-left-width: 1px; 
                                                            		   padding: 5px 5px 130px 5px; width:50%;">
                                                                <p>Ulica i broj, poštanski broj, mesto i država:</p>
                                                                <P>
                                                               			<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Adresa/zaj:Ulica"/> <xsl:text></xsl:text> &#160;
									                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Adresa/zaj:Broj"/> <xsl:text></xsl:text>,
									                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Adresa/zaj:Postanski_broj"/> <xsl:text></xsl:text> &#160;
									                                    <xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Adresa/zaj:Grad"/> <xsl:text></xsl:text>
                                
                                                                </P>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td></tr>
                                            </table>
                                        </td>
                                        <td style="width:30%" >
                                            <table style="margin:0; border-left:1px; 
                                            			border-collapse: collapse;
														border-left-style: solid;
														border-left-width: 1px;">
                                                <tr><td style="padding: 5px 5px 15px 5px;"><p>Broj telefona:</p>
														<p>
															<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Kontakt_podaci/zaj:Telefon"/> <xsl:text></xsl:text> &#160;
														</p>
														</td></tr>
                                                <tr><td style="border-collapse: collapse;
													border-top-style: solid;
													border-top-width: 1px; 
													padding: 5px 5px 15px 5px;"><p>Broj faksa:</p>
														<p>
															<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Kontakt_podaci/zaj:Faks"/> <xsl:text></xsl:text> &#160;
														</p>
												</td></tr>
	                                            <tr><td style="border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px; 
														border-left-style: solid;
														border-left-width: 1px;
														padding: 5px 5px 15px 5px;"><p>E-pošta:</p>
														<p>
															<xsl:value-of select="p:Zahtev_za_priznanje_patenta/p:Punomocnik/p:Lice/zaj:Kontakt_podaci/zaj:Email"/> <xsl:text></xsl:text> &#160;
														</p>
												</td></tr>
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
                                    <td style="width:20%; ">
                                        <p class="row-title">Polje broj V </p>
                                    </td>
                                    <td style="width:80%; ">
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
                        <td style="padding: 10px 5px 0 5px; border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px;">
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
                    	<xsl:variable name="nacin_dostavljanja" select="p:Zahtev_za_priznanje_patenta/p:Podaci_o_dostavljanju/p:Nacin_dostavljanja"/>
                        <xsl:if test="$nacin_dostavljanja='elektronski'">
	                                    
	                        <td style="padding: 5px 5px 5px 5px; margin:0; border-collapse: collapse;
															border-top-style: solid;
															border-top-width: 1px;">
	                            <p>Podnosilac prijave je saglasan da Zavod vrši dostavljanje pismena isključivo elektronskim putem u formi elektronskog dokumenta (u ovom slučaju neophodna je registracija na portalu „eUprave”)</p>
	                        </td>
                        </xsl:if>
                    </tr>
                    <tr>
                    	<xsl:variable name="nacin_dostavljanja" select="p:Zahtev_za_priznanje_patenta/p:Podaci_o_dostavljanju/p:Nacin_dostavljanja"/>
                    	<xsl:if test="$nacin_dostavljanja='papirna_forma'">
                        <td style="padding: 0px 5px 5px 5px; margin:0;">
                            <p>Podnosilac prijave je saglasan da Zavod vrši dostavljanje pismena u papirnoj formi </p>
                        </td>
                        </xsl:if>
                    </tr>
                    <tr>
                        <td class="line" style="padding: 10px 5px 5px 5px;">
                            <table style="margin:0;">
                                <tr>
                                    <td style="width:20%">
                                        <p class="row-title">Polje broj VII </p>
                                    </td>
                                    <xsl:variable name="tip_prijave" select="p:Zahtev_za_priznanje_patenta/@tip_prijave"/>
                        			<xsl:if test="$tip_prijave='dopunska'">
	                                    <td style="width:70%">
	                                        <p class="row-title">DOPUNSKA PRIJAVA  </p>
	                                    </td>
                                    </xsl:if>
                                    <xsl:if test="$tip_prijave='izdvojena'">
	                                    <td style="width:70%">
	                                        <p class="row-title">IZDVOJENA PRIJAVA  </p>
	                                    </td>
                                    </xsl:if>
                                    <td style="width:6%">
                                        <p class="row-title">(61)/(62)  </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding: 10px 5px 0 5px; border-collapse: collapse;
														border-top-style: solid;
														border-top-width: 1px;">
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
                <table  style="border: 1px; margin-top:0;">
                    <thead>
                        <tr>
                            <td style="width:4%; text-align:center;  padding:5px; 
                            						border-collapse: collapse;
													border-right-style: solid;
													border-right-width: 1px;">Br.</td>
                            <td style="width:32%; text-align:center; border-left:1px;  padding:5px;">Datum podnošenja ranije prijave</td>
                            <td style="width:32%; text-align:center; 
                       								border-collapse: collapse;
													border-right-style: solid;
													border-right-width: 1px;
													border-left-style: solid;
													border-left-width: 1px;  padding:5px;">Broj ranije prijave</td>
                            <td style="width:32%; text-align:center; padding:5px;">Dvoslovna oznaka države, regionalne ili međunarodne organizacije</td>
                        </tr>
                        <tr>
                        <xsl:for-each select="p:Zahtev_za_priznanje_patenta/p:Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava">
                            <td style="width:4%; border-collapse: collapse;
														border-right-style: solid;
														border-right-width: 1px;
														padding-top: 5px; border-top-style: solid;
														border-top-width: 1px;">
														<xsl:value-of select="position()"/>
							</td>
                            <td style="width:32%; padding-top: 5px; border-top-style: solid;
														border-top-width: 1px; ">
														<p>
															<xsl:value-of select="p:Ranija_prijava/p:Datum_podnosenja"/> <xsl:text></xsl:text>
														</p>
							</td>
                            <td style="width:32%; border-collapse: collapse;
														border-left-style: solid;
														border-left-width: 1px; 
														border-right-style: solid;
														border-right-width: 1px;
														border-top-style: solid;
														border-top-width: 1px; 
														padding-top: 5px;">
														<p>
															<xsl:value-of select="p:Ranija_prijava/p:Broj_prijave"/> <xsl:text></xsl:text>
														</p>
							</td>
                            <td style="width:32%; padding-top: 5px; border-top-style: solid;
														border-top-width: 1px; ">
														<p>
															<xsl:value-of select="p:Ranija_prijava/p:Dvoslovna_oznaka_drzave"/> <xsl:text></xsl:text>
														</p>
							</td>
                        </xsl:for-each>
                        </tr>
                    </thead>
                </table>
                
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>