<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:tei="http://www.tei-c.org/ns/1.0"
  version="2.0"
>

<xsl:output method="text"/>
<xsl:key name="o" use="1" match="@oxpID"/>


<xsl:template match="/">
<xsl:variable name="x">
  <xsl:for-each select="key('o',1)">
    <xsl:sort select="." data-type="number"/>
    <n><xsl:value-of select="."/></n>
  </xsl:for-each>
</xsl:variable>
<xsl:for-each select="$x/n[last()]"> 	
  <xsl:value-of select="."/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>
