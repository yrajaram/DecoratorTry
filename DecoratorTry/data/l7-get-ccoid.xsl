<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:b2b="http://tools.herakles.com/types" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 
<xsl:template match="node() | @*">
<xsl:copy>
<xsl:apply-templates select="node() | @*"/>
</xsl:copy>
</xsl:template>
 
<xsl:template match="soap:Header">
<xsl:copy>
<b2b:wsx>
<b2b:user>Gee boom ba</b2b:user>
</b2b:wsx>
<xsl:apply-templates select="@*"/>
<xsl:apply-templates/>
</xsl:copy>
 
</xsl:template>
</xsl:stylesheet>