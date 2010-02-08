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
      <xsl:result-document href="99999999.xml" method="xml"
			   indent="yes">
	<place type="university">
	  <placeName>University of Oxford</placeName>
	  <xsl:for-each select="//tei:place[@type='building' and @obnCode]">
	      <relation type="occupies"
			passive="#{@oxpID}">
		<xsl:choose>
		  <xsl:when test="tei:event/@type='acquisition'">
		    <xsl:attribute name="from" select="tei:event[@type='acquisition']/@when"/>
		  </xsl:when>
		</xsl:choose>
	      </relation>
	  </xsl:for-each>
	</place>
      </xsl:result-document>
    <xsl:for-each select="key('ALLPLACES',1)">
      <xsl:result-document href="{@oxpID}.xml" method="xml" indent="yes">
	<xsl:copy>
	  <xsl:apply-templates select="@*"/>
	  <xsl:for-each select="tei:event">
	    <xsl:choose>
	    <!--
	    <xsl:when test="@type='acquisition'">
	      <relation type="occupies" />
	    </xsl:when>
	    -->
	    <xsl:when test="@type='construction'">
	      <xsl:attribute name="from" select="@when"/>
	    </xsl:when>
	    <xsl:when test="@type='ended'">
	      <xsl:attribute name="to" select="@when"/>
	    </xsl:when>
	    <xsl:when test="@type='founded'">
	      <xsl:attribute name="from" select="@when"/>
	    </xsl:when>
	    <xsl:otherwise>
	    </xsl:otherwise>
	    </xsl:choose>
	  </xsl:for-each>
	  <xsl:if test="@oucsCode">
	    <idno type="oucs"><xsl:value-of
	    select="@oucsCode"/></idno>
	  </xsl:if>
	  <xsl:if test="@obnCode">
	    <idno type="obn"><xsl:value-of
	    select="@obnCode"/></idno>
	  </xsl:if>
	  <xsl:if test="@olisCode">
	    <idno type="oliscode"><xsl:value-of
	    select="@olisCode"/></idno>
	  </xsl:if>	
	  <xsl:if test="@olisID">
	    <idno type="olis"><xsl:value-of
	    select="@olisID"/></idno>
	  </xsl:if>	
	  <xsl:apply-templates 
	      select="*|processing-instruction()|comment()|text()"/>
	  <xsl:for-each select="tei:place[not(@type='room')]">
	    <relation name="occupies"
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
  
  <xsl:template match="tei:event"/>

  <xsl:template match="tei:relation">
    <xsl:copy>
      <xsl:copy-of select="@passive"/>
      <xsl:copy-of select="@name"/>
      <xsl:copy-of select="@type"/> 
   </xsl:copy>
  </xsl:template>
  
  <xsl:template match="*">
    <xsl:copy>
      <xsl:apply-templates 
	  select="*|@*|processing-instruction()|comment()|text()"/>
    </xsl:copy>
  </xsl:template>
  
  <xsl:template match="@olisID"/>
  <xsl:template match="@olisCode"/>
  <xsl:template match="@oucsCode"/>
  <xsl:template match="@obnCode"/>
</xsl:stylesheet>