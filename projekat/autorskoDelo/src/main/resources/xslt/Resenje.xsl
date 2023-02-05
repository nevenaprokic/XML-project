<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:re="http://ftn.uns.ac.rs/resenje"  
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:str="http://exslt.org/strings" extension-element-prefixes="str">
	<xsl:output method="html" encoding="UTF-8" indent="yes" />

	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="content-type"
					content="application/xhtml+xml; charset=UTF-8" />
				<title>Resenje o priznanju autorskog dela </title>

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
					border-collapse: collapse;
					border-style: solid;
					border-width: 1px;
					}
					tr, td { border-collapse: collapse;
					border-style: solid;
					border-width: 1px;
					margin:0; padding:10px;}

					.title{
					margin: 0;text-align: center; font-weight:bold; font-size: 25px;
					border-width:10px;
					}
					.title-request{
					margin: 0;text-align: center; font-weight:bold; font-size: 20px;
					border-width:10px; margin-bottom: 20px;
					}
					.subtitle{
					margin:0; font-weight:bold; text-align: center; 
					}
					.subtitle-date{
					margin:0; font-weight:bold; text-align: center; margin-bottom: 30px;
					}
					.header{
					width:30%;
					}

				</style>
			</head>
			<body>
				<div>
					<p class="title">
						<xsl:variable name="lowercase" select="'abcdefghijklmnopqrstuvwxyz'" />
						<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
						<xsl:value-of select="translate(re:Resenje/@status, $lowercase, $uppercase)" />O RESENJE
					</p>
					<p class="title-request">
						ZAHTEVA ZA AUTORSKO DELO
						BR. <span><xsl:value-of select="re:Resenje/re:Dodatak/re:Sifra"/><xsl:text></xsl:text></span>
					</p>
					<p class="subtitle">Rešenje izdao službenik: </p>
					<p class="subtitle-date">
						<span>
							<xsl:value-of select="re:Resenje/re:Sluzbenik/re:Ime"/> <xsl:text>&#160;</xsl:text>
							<xsl:value-of select="re:Resenje/re:Sluzbenik/re:Prezime"/>
						</span>
					</p>
					<p class="subtitle-date">
						Datum izdavanja:
						<span><xsl:value-of select="re:Resenje/@datum_resenja"/><xsl:text></xsl:text></span>
					</p>
				</div>
			</body>
			<table>
				<tbody>
					<tr>
					<xsl:choose>
						<xsl:when test="re:Resenje/@status = 'odobren'">
                        	<td class="header">
								Ovim resenjem priznaje se Zahtev za priznanje i deponovanje autorskog dela zavedeno pod brojem
								<span><xsl:value-of select="re:Resenje/re:Dodatak/re:Sifra"/><xsl:text></xsl:text></span>.
								<br></br>
								<br></br>
								Resenje o priznavanju postaje pravnosnazno na dan izdavanja.
							</td>	
                        </xsl:when>
                        <xsl:when test="re:Resenje/@status = 'odbijen'">
                        	<td class="header">
								Ovim resenjem odbija se Zahtev za priznanje i deponovanje autorskog dela zavedeno pod brojem
								<span><xsl:value-of select="re:Resenje/re:Dodatak/re:Sifra"/><xsl:text></xsl:text></span>.
								Nakon uvida u zahtev, doneta je odluka o odbijanju.
								<br></br> 
								<br></br>
								OBRAZLOZENJE:
								<br></br>
								<span><xsl:value-of select="re:Resenje/re:Dodatak/re:Obrazlozenje"/><xsl:text></xsl:text></span>.
								<br></br>
								<br></br>
								Resenje o odbijanju zahteva postaje pravnosnazno na dan izdavanja.
							</td>
                        </xsl:when>
	                 </xsl:choose>
					</tr>
				</tbody>
			</table>
		</html>
	</xsl:template>

</xsl:stylesheet>