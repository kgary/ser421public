<?xml version="1.0"?> 
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="text" encoding="UTF-8" indent="no"/>

<xsl:template match="person">
	<xsl:text>insert into person (fname, lname, street, city, state, zip) values (</xsl:text>
	<xsl:text>'</xsl:text><xsl:value-of select="name/first" /><xsl:text>',</xsl:text>
	<xsl:text>'</xsl:text><xsl:value-of select="name/last" /><xsl:text>',</xsl:text>
	<xsl:text>'</xsl:text><xsl:value-of select="address/street" /><xsl:text>',</xsl:text>
	<xsl:text>'</xsl:text><xsl:value-of select="address/city" /><xsl:text>',</xsl:text>
	<xsl:text>'</xsl:text><xsl:value-of select="address/state" /><xsl:text>',</xsl:text>
	<xsl:text>'</xsl:text><xsl:value-of select="address/zip" />
	<xsl:text>');</xsl:text>
</xsl:template>
</xsl:stylesheet>
