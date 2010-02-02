<xsl:stylesheet
    version="2.0"
    xmlns:html="http://www.w3.org/1999/xhtml"
    exclude-result-prefixes="tei html"                
    xmlns="http://www.tei-c.org/ns/1.0"
    xmlns:tei="http://www.tei-c.org/ns/1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>

  <xsl:key name="ALLPLACES" match="tei:place[not(@type='room')]" use="1"/>
  <xsl:key name="PLACES" match="tei:place" use="@oxpID"/>
  <xsl:key name="RELS" match="tei:relation" use="substring-after(@active,'#')"/>
  <xsl:template match="/">
    <xsl:for-each select="key('ALLPLACES',1)">
      <xsl:result-document href="{@oxpID}.xml" method="xml" indent="yes">
	<xsl:copy>
	  <xsl:apply-templates 
	      select="*|@*|processing-instruction()|comment()|text()"/>
	  <xsl:for-each select="tei:place[not(@type='room')]">
	    <relation name="occupies"
		      active="#{parent::tei:place/@oxpID}"
		      passive="#{@oxpID}">
	      <xsl:attribute name="type">
		<xsl:text>geo</xsl:text>
		<xsl:if test="not(preceding-sibling::tei:place)">
		  <xsl:text> primary</xsl:text>
		</xsl:if>
	      </xsl:attribute>
	    </relation>
	  </xsl:for-each>
	  <xsl:apply-templates select="key('RELS',@oxpID)"/>
	</xsl:copy>
      </xsl:result-document>
    </xsl:for-each>
  </xsl:template>

  <xsl:template match="tei:place[parent::tei:place and not(@type='room')]"/>

  <xsl:template match="@*|text()|comment()|processing-instruction()">
    <xsl:copy-of select="."/>
  </xsl:template>
  
  
  <xsl:template match="*">
    <xsl:copy>
      <xsl:apply-templates 
	  select="*|@*|processing-instruction()|comment()|text()"/>
    </xsl:copy>
  </xsl:template>
  
	
</xsl:stylesheet>