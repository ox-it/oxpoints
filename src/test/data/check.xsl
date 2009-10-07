<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:tei="http://www.tei-c.org/ns/1.0"
  version="2.0"
>

<xsl:key name="I" use="@oucsCode" match="*"/>
<xsl:key name="I" use="@olisCode" match="*"/>

<xsl:key name="Oxp_All" match="*[@oxpID]" use="1"/>
<xsl:key name="O_All" match="*[@obnCode]" use="1"/>
<xsl:key name="O" use="@obnCode" match="*[@obnCode]"/>
<xsl:key name="Oxp" use="@oxpID" match="*[@oxpID]"/>
<xsl:key name="Occupies"
	 match="tei:relation[contains(@type,'primary')]"
	 use="@active"/>

<xsl:output method="xml" indent="yes" encoding="utf-8"/>


<xsl:template match="/">
  <xsl:for-each select="key('O_All',1)">
    <xsl:if test="count(key('O',@obnCode))&gt;1">
      <xsl:message>obnCode <xsl:value-of select="@obnCode"/> occurs more than once</xsl:message>
    </xsl:if>
  </xsl:for-each>
  <xsl:for-each select="key('Oxp_All',1)">
    <xsl:if test="count(key('Oxp',@oxpID))&gt;1">
      <xsl:message>oxpID <xsl:value-of select="@oxpID"/> occurs more than once</xsl:message>
    </xsl:if>
  </xsl:for-each>
    <xsl:for-each select="//tei:place">
      <xsl:if
	  test="count(key('Occupies',concat('#',@oucsCode)))&gt;1">
	<xsl:message>Place <xsl:value-of
	select="@oxpID"/>/<xsl:value-of select="@oucsCode"/> has more than one primary place</xsl:message>
      </xsl:if>
      <xsl:if test="not(@oxpID)">
	<xsl:message>Place <xsl:value-of
	select="@oxpID"/>/<xsl:value-of select="@oucsCode"/> has no oxpid attribute</xsl:message>
      </xsl:if>
      <xsl:if test="not(@type)">
	<xsl:message>Place <xsl:value-of
	select="@oxpCode"/>/<xsl:value-of select="@oucsCode"/> has no type attribute</xsl:message>
      </xsl:if>
      <xsl:if test="(@type='building' and not(.//tei:geo))">
	<xsl:message>Place <xsl:value-of
	select="tei:placeName"/>:<xsl:value-of
	select="../tei:placeName"/>:<xsl:value-of
	select="../../tei:placeName"/>: <xsl:value-of
	select="@obnCode"/>/<xsl:value-of select="@oucsCode"/> has no location</xsl:message>
      </xsl:if>
    </xsl:for-each>
  <xsl:for-each select="//tei:relation">
    <xsl:if test="not(key('Oxp',substring-after(@passive,'#')))">
      <xsl:message>cannot find anything to point <xsl:value-of
      select="@passive"/> at</xsl:message>
    </xsl:if>
    <xsl:if test="not(key('Oxp',substring-after(@active,'#')))">
      <xsl:message>cannot find anything to point <xsl:value-of
      select="@active"/> from</xsl:message>
    </xsl:if>
  </xsl:for-each>
  <xsl:for-each select="//tei:figure">
    <xsl:if test="not(key('Oxp',substring-after(@corresp,'#')))">
      <xsl:message>cannot find anything to point <xsl:value-of
      select="@corresp"/> at</xsl:message>
    </xsl:if>
  </xsl:for-each>
</xsl:template>


</xsl:stylesheet>
