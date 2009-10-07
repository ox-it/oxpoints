<xsl:stylesheet 
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns="http://www.tei-c.org/ns/1.0"  
    xmlns:tei="http://www.tei-c.org/ns/1.0"  
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    exclude-result-prefixes="tei xs"
    version="2.0"
>

<!-- identity transform -->
<xsl:output method="xml" indent="yes" encoding="utf-8"/>

<xsl:key name="byOLIS" match="tei:place" use="@olisCode"/>
<xsl:key name="byOUCS" match="tei:place" use="@oucsCode"/>

<xsl:template match="tei:figure">
  <xsl:copy>
    <xsl:variable name="corresp">
      <xsl:value-of select="substring-after(@corresp,'#')"/>
    </xsl:variable>
    <xsl:attribute name="corresp">
      <xsl:text>#</xsl:text>
      <xsl:choose>
	<xsl:when test="key('byOUCS',$corresp)">
	  <xsl:for-each select="key('byOUCS',$corresp)">
	    <xsl:value-of select="@oxpID"/>
	  </xsl:for-each>
	</xsl:when>
	<xsl:when test="key('byOLIS',$corresp)">
	  <xsl:for-each select="key('byOLIS',$corresp)">
	    <xsl:value-of select="@oxpID"/>
	  </xsl:for-each>
	</xsl:when>
	<xsl:otherwise>
	  <xsl:message terminate="yes">DISASTER with <xsl:value-of select="$corresp"/></xsl:message>
	</xsl:otherwise>
      </xsl:choose>
    </xsl:attribute>
    <xsl:copy-of select="*"/>
  </xsl:copy>
</xsl:template>
<xsl:template match="tei:relation">
  <xsl:copy>
    <xsl:copy-of select="@name"/>
    <xsl:copy-of select="@type"/>
    <xsl:variable name="active">
      <xsl:value-of select="substring-after(@active,'#')"/>
    </xsl:variable>
    <xsl:variable name="passive">
      <xsl:value-of select="substring-after(@passive,'#')"/>
    </xsl:variable>
    <xsl:attribute name="active">
      <xsl:text>#</xsl:text>
      <xsl:choose>
	<xsl:when test="key('byOUCS',$active)">
	  <xsl:for-each select="key('byOUCS',$active)">
	    <xsl:value-of select="@oxpID"/>
	  </xsl:for-each>
	</xsl:when>
	<xsl:when test="key('byOLIS',$active)">
	  <xsl:for-each select="key('byOLIS',$active)">
	    <xsl:value-of select="@oxpID"/>
	  </xsl:for-each>
	</xsl:when>
	<xsl:otherwise>
	  <xsl:message terminate="yes">DISASTER with <xsl:value-of select="$active"/></xsl:message>
	</xsl:otherwise>
      </xsl:choose>
    </xsl:attribute>
    <xsl:attribute name="passive">
      <xsl:text>#</xsl:text>
      <xsl:choose>
	<xsl:when test="key('byOUCS',$passive)">
	  <xsl:for-each select="key('byOUCS',$passive)">
	    <xsl:value-of select="@oxpID"/>
	  </xsl:for-each>
	</xsl:when>
	<xsl:when test="key('byOLIS',$passive)">
	  <xsl:for-each select="key('byOLIS',$passive)[1]">
	    <xsl:value-of select="@oxpID"/>
	  </xsl:for-each>
	</xsl:when>
	<xsl:otherwise>
	  <xsl:message terminate="yes">DISASTER</xsl:message>
	</xsl:otherwise>
      </xsl:choose>
    </xsl:attribute>
  </xsl:copy>
</xsl:template>

<!-- identity transform -->
<xsl:template match="@*|text()|comment()|processing-instruction()">
 <xsl:copy-of select="."/>
</xsl:template>

<xsl:template match="*">
  <xsl:copy>
    <xsl:apply-templates 
	select="@*|*|processing-instruction()|comment()|text()"/>
  </xsl:copy>
</xsl:template>

</xsl:stylesheet>
