<?xml version="1.0"?>
<xsl:stylesheet version="2.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" encoding="UTF-8" indent="no"/>

<xsl:template match="person">
  <html>
    <head>
      <title><xsl:value-of select="name/last/text()"/>, <xsl:value-of select="name/first/text()"/></title>
    </head>
    <body bgcolor="#ffffff">
      <table border="3">
	<tr>
	  <td>
	    <xsl:apply-templates select="name/first/text()"/>
	  </td>
	  <td>
	    <xsl:apply-templates select="name/last/text()"/>
	  </td>
	  <td>
	    <xsl:apply-templates select="email/text()"/>
	  </td>
	  <td>
	    <xsl:if test="address">
	      Address: <xsl:apply-templates select="address"/>
	    </xsl:if>
	  </td>
	</tr>
      </table>
    </body>
  </html>

  <xsl:template match="person/name|person/address|person/email"> 
    <xsl:value-of select="text()"/>
  </xsl:template>
</xsl:template>
</xsl:stylesheet>
