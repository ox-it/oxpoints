<?xml version="1.0"?>

<!-- Subversion $Id: directory2html.xslt 30932 2004-07-29 17:35:38Z vgritsenko $ -->

<xsl:stylesheet 
                exclude-result-prefixes="tei"
		xmlns:tei="http://www.tei-c.org/ns/1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		version="2.0">
  <xsl:param name="path">.</xsl:param>

  <xsl:key name="IDS" use="@oxpID" match="tei:place"/>

  <xsl:template name="main">
    <xsl:variable name="pathlist">
    <xsl:value-of select="concat($path,'?select=*.xml;recurse=yes;on-error=warning')"/> </xsl:variable>
    <xsl:variable name="docs"  select="collection($pathlist)"/> 
    <xsl:variable name="all">
      <xsl:for-each select="$docs/tei:place[not(parent::*)]">
	<xsl:copy-of select="."/>
      </xsl:for-each>
    </xsl:variable>
    <xsl:for-each select="$all/tei:place">
      <xsl:if test="count(key('IDS',@oxpID))&gt;1">
	<xsl:message>Duplicate ID <xsl:value-of select="@oxpID"/>
	</xsl:message>
      </xsl:if>
      <xsl:for-each select="tei:relation">
	<xsl:variable name="n" select="substring-after(@passive,'#')"/>
	<xsl:if test="not(key('IDS',$n))">
	  <xsl:message>ID <xsl:value-of select="$n"/> not found, referred to by <xsl:value-of select="ancestor::tei:place/@oxpID"/></xsl:message>
	</xsl:if>
      </xsl:for-each>
    </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>
