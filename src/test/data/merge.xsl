<xsl:stylesheet 
  xmlns="http://www.tei-c.org/ns/1.0"  
  xmlns:tei="http://www.tei-c.org/ns/1.0"  
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  exclude-result-prefixes="tei"
  version="1.0"
>

<!-- identity transform -->
<xsl:output method="xml" indent="yes" encoding="utf-8"/>

<xsl:key name="N" match="obn" use="placeName"/>

<xsl:key name="OBN" use="@obnCode" match="tei:place"/>

<xsl:template match="@*|text()|comment()|processing-instruction()">
 <xsl:copy-of select="."/>
</xsl:template>


<xsl:template match="/">
  <xsl:variable name="O" select="/"/>
  <xsl:apply-templates/>
  <xsl:for-each select="document('BuildingList.xml')">
    <xsl:for-each select="//obn">
      <xsl:variable name="c" select="code"/>
      <xsl:variable name="p" select="placeName"/>
      <xsl:variable name="cons" select="construction"/>
      <xsl:variable name="acq" select="acquisition"/>
      <xsl:variable name="N" select="position()"/>
      <xsl:for-each select="$O">
	<xsl:if test="not(key('OBN',$c))">
	  <place type="building" obnCode="{$c}">
	    <xsl:attribute name="oucsCode">
	      <xsl:value-of select="number(23233682 + $N)"/>
	    </xsl:attribute>
	    <xsl:attribute name="oxpID">
	      <xsl:text>e_</xsl:text>
	      <xsl:value-of select="$N"/>
	    </xsl:attribute>
	    <placeName>
	      <xsl:value-of select="$p"/>
	    </placeName>
	    <xsl:if test="string-length($cons) &gt; 1">
	      <event when="{$cons}" type="construction"/>
	    </xsl:if>
	    <xsl:if test="string-length($acq) &gt; 1">
	      <event when="{$acq}" type="acquisition"/>
	    </xsl:if>
	  </place>
	</xsl:if>
      </xsl:for-each>
    </xsl:for-each>
  </xsl:for-each>
</xsl:template>
<xsl:template match="*">
  <xsl:copy>
    <xsl:apply-templates 
	select="*|@*|processing-instruction()|comment()|text()"/>
  </xsl:copy>
</xsl:template>

<xsl:template match="tei:place[@type='building']">
  <xsl:copy>
    <xsl:apply-templates 
	select="@*"/>
    <xsl:variable name="name">
      <xsl:value-of select="tei:placeName"/>
    </xsl:variable>
    <xsl:variable name="id">
      <xsl:value-of select="oucsCode"/>
    </xsl:variable>
    <xsl:variable name="address">
      <xsl:value-of select="normalize-space(tei:location[@type='address'])"/>
    </xsl:variable>
    <xsl:variable name="parentname">
      <xsl:value-of select="ancestor::tei:place[1]/tei:placeName"/>
    </xsl:variable>
    <xsl:if test="not(@obnCode)">
    <xsl:for-each select="document('BuildingList.xml')">
      <xsl:choose>
	<xsl:when test="count(key('N',$name))=1">
	  <xsl:message>Found match for  <xsl:value-of select="$name"/></xsl:message>
	  <xsl:for-each select="key('N',$name)">
	    <xsl:attribute name="obnCode">
	      <xsl:value-of select="code"/>
	    </xsl:attribute>
	    <xsl:for-each select="construction">
	      <event when="{.}" type="construction"/>
	    </xsl:for-each>
	    <xsl:for-each select="acquisition">
	      <event when="{.}" type="acquisition"/>
	    </xsl:for-each>
	  </xsl:for-each>
	</xsl:when>
	<xsl:when test="count(key('N',$name))&gt;1">
	  <xsl:message>Found MULTIPLE matches for  <xsl:value-of
	  select="$name"/></xsl:message>
	</xsl:when>
	<xsl:when test="$name='NERC Centre for Ecology and Hydrology'"/>
	<xsl:when test="$parentname='European Humanities Research Centre'"/>
	<xsl:when test="$name='Oxford Union Society'"/>
	<xsl:when test="$name='Rhodes House'"/>
	<xsl:when test="$name='Security Service'"/>
	<xsl:when test="$name='57 Woodstock Road'"/>
	<xsl:when test="$parentname='Oxford University Press'"/>
	<xsl:when test="$parentname='Blackfriars'"/>
	<xsl:when test="$parentname='Christ Church'"/>
	<xsl:when test="contains($parentname,'Greyfriar')"/>
	<xsl:when test="contains($parentname,'St Stephen')"/>
	<xsl:when test="$parentname='Christ Church Cathedral'"/>
	<xsl:when test="$parentname='Christ Church Picture Gallery'"/>
	<xsl:when test="$parentname='The University Church of St Mary the Virgin'"/>
	<xsl:when test="contains($name,' College') or
			contains($parentname,' College')"/>
	<xsl:when test="contains($name,' Hall') or contains($parentname,' Hall')"/>
	<xsl:when test="$parentname='Oxford University Student Union'"/>
	<xsl:when test="$name=''">
	  <xsl:message>NO MATCH. <xsl:value-of select="$id"/>. Empty name. Parent is <xsl:value-of select="$parentname"/></xsl:message>
	</xsl:when>
	<xsl:when test="key('N',$address)">
	  <xsl:message>PARTIAL MATCH. <xsl:value-of select="$id"/>.Parent is <xsl:value-of select="$parentname"/> BUT
	  see <xsl:value-of select="$address"/> ie <xsl:value-of select="key('N',$address)/obn"/></xsl:message>
	</xsl:when>
	<xsl:otherwise>
	  <xsl:message>NO MATCH. <xsl:value-of
	  select="$id"/>. <xsl:value-of select="$name"/>. parent is <xsl:value-of select="$parentname"/></xsl:message>
	</xsl:otherwise>
      </xsl:choose>
	</xsl:for-each>
    </xsl:if>
    <xsl:apply-templates 
	select="*|processing-instruction()|comment()|text()"/>
  </xsl:copy>
</xsl:template>

</xsl:stylesheet>
