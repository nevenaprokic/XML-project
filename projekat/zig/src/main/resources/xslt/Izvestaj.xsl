<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:iz="http://ftn.uns.ac.rs/izvestaj"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:str="http://exslt.org/strings" extension-element-prefixes="str">
	<xsl:output method="html" encoding="UTF-8" indent="yes" />

	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="content-type"
					content="application/xhtml+xml; charset=UTF-8" />
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
					<p class="title">IZVESTAJ</p>
					<p class="title-request">ZAHTEVA ZA ZIG</p>
					<p class="subtitle">Za period: </p>
					<p class="subtitle-date">
						<span><xsl:value-of select="iz:Izvestaj/@pocetni_datum_izvestaja"/><xsl:text></xsl:text></span> - 
						<span><xsl:value-of select="iz:Izvestaj/@krajnji_datum_izvestaja"/><xsl:text></xsl:text></span>
					</p>
				</div>
				<table>
					<tbody>
						<tr>
							<td class="header">Broj podnetih zahteva</td>
							<td><xsl:value-of select="iz:Izvestaj/iz:BrojPodnetihZahteva"/> <xsl:text></xsl:text></td>
						</tr>
						<tr>
							<td class="header">Broj prihvacenih zahteva</td>
							<td><xsl:value-of select="iz:Izvestaj/iz:BrojPrihvacenihZahteva"/> <xsl:text></xsl:text></td>
						</tr>
						<tr>
							<td class="header">Broj odbijenih zahteva</td>
							<td><xsl:value-of select="iz:Izvestaj/iz:BrojOdbijenihZahteva"/> <xsl:text></xsl:text></td>
						</tr>
					</tbody>
				</table>

			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>