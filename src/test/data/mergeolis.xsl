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

<xsl:key name="U" match="library" use="@derived_library_id"/>

<xsl:key name="PLACE" match="tei:place" use="@olisCode"/>
<xsl:key name="LIBCODE"  match="tei:place[@olisCode]" use="@olisCode"/>
<xsl:key name="REL"  match="tei:relation[@name='occupies']" use="substring-after(@active,'#')"/>


<xsl:template match="tei:place[@olisCode]"/>
<xsl:template match="tei:relation[starts-with(@active,'#O_lib_') or starts-with(@passive,'#O_lib_')]"/>
<xsl:variable name="ORIG" select="/"/>



<xsl:template match="tei:listPlace">
<xsl:for-each select="tei:place[@type='library']//tei:geo">
  <xsl:message><xsl:value-of
  select="ancestor::tei:place/tei:placeName"/> has a location</xsl:message>
</xsl:for-each>
  <xsl:copy>
    <xsl:apply-templates 
	select="@*|*|processing-instruction()|comment()|text()"/>
  </xsl:copy>
  <listPlace>
    <xsl:for-each select="document('olis_codes.xml')/root/olis_institutions/olis_institution">
      <!--
	  <olis_institution main_name="All Souls College Library" derived_olis_key="All Souls"
	  short_name="All Souls" three_letter_code="ASC">
	  <matches_library derived_library_id="ASC"/>
	  <olis_sublocations>
	  <olis_sublocation main_name="ASC Anson 1st" derived_olis_key="ASC Anson 1st"
	  description="Anson Room, 1st floor"/>
      -->
      <xsl:variable name="N">
	<xsl:number level="any"/>
      </xsl:variable>
	<place oxpID="{32320000 + $N cast as xs:integer}"
	       type="library" 
	       olisCode="{@derived_olis_key}"
	       olisID="{@three_letter_code}">
	    <xsl:variable name="code">
		<xsl:value-of select="@three_letter_code"/>
	    </xsl:variable>

	  <xsl:attribute name="oucsCode">
	    <xsl:for-each select="$ORIG">
	    <xsl:choose>
	      <xsl:when test="key('PLACE',$code)">
		<xsl:for-each
		    select="key('PLACE',$code)">
		<xsl:value-of select="@oucsCode"/>
		</xsl:for-each>
	      </xsl:when>
	      <xsl:otherwise>
		<xsl:text>OLIS_</xsl:text>
		<xsl:value-of select="$code"/>
	      </xsl:otherwise>
	    </xsl:choose>
	    </xsl:for-each>
	  </xsl:attribute>

	  <placeName><xsl:value-of select="@main_name"/></placeName>
	  <xsl:if test="key('U',@three_letter_code)">
	    <xsl:for-each select="key('U',@three_letter_code)">
	      <!--
		  <url_of_library_guide>http://www.lib.ox.ac.uk/libraries/guides/STA.html</url_of_library_guide>
		  <url_of_library_webpage>http://www.stats.ox.ac.uk/about_us/library</url_of_library_webpage>
	      -->
	      <xsl:if test="url_of_library_guide">
		<trait type="liburl">
		  <desc>
		    <ptr target="{url_of_library_guide}"/>
		  </desc>
		</trait>
	      </xsl:if>
	      <xsl:if test="url_of_library_webpage">
		<trait type="url">
		  <desc>
		    <ptr target="{url_of_library_webpage}"/>
		  </desc>
		</trait>
	      </xsl:if>
	    </xsl:for-each>
	  </xsl:if>
	</place>
	<xsl:for-each select="olis_sublocations/olis_sublocation">
	  <xsl:variable name="N">
	    <xsl:number level="any"/>
	  </xsl:variable>
	  <xsl:variable name="ID">
	    <xsl:value-of select="32330000 + $N cast as xs:integer"/>
	  </xsl:variable>
	  <place oxpID="{$ID}"
		 type="sublibrary" olisCode="{@derived_olis_key}">
	  <xsl:copy-of select="@asr_sublocation_type"/>
	    <placeName><xsl:value-of select="@description"/></placeName>
	  </place>
	  <relation name="controls"
		    active="#{ancestor::olis_institution/@derived_olis_key}"
		    passive="#{@derived_olis_key}"/>
	  <xsl:call-template name="findLoc">
	    <xsl:with-param name="code1">
	      <xsl:value-of select="@derived_olis_key"/>
	    </xsl:with-param>
	    <xsl:with-param name="code2">
	      <xsl:value-of select="@three_letter_code"/>
	    </xsl:with-param>
	  </xsl:call-template>
	</xsl:for-each>
	  <xsl:call-template name="findLoc">
	    <xsl:with-param name="code1">
	      <xsl:value-of select="@derived_olis_key"/>
	    </xsl:with-param>
	    <xsl:with-param name="code2">
	      <xsl:value-of select="@three_letter_code"/>
	    </xsl:with-param>
	  </xsl:call-template>
      </xsl:for-each>
  </listPlace>

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

<xsl:template name="findLoc">
  <xsl:param name="code2"/>
  <xsl:param name="code1"/>

  <xsl:for-each select="$ORIG">
    <xsl:for-each select="key('LIBCODE',$code2)">
      <xsl:variable name="place">
	<xsl:value-of select="@olisCode"/>
      </xsl:variable>
      <xsl:for-each select="key('REL',@oucsCode)">
	<relation name="occupies" active="#{$code1}"
		  passive="{@passive}"/>
	<xsl:message>relation <xsl:value-of select="$code1"/> -- <xsl:value-of select="@passive"/></xsl:message>
      </xsl:for-each>
    </xsl:for-each>
    <xsl:for-each select="key('LIBCODE',$code1)">
      <xsl:variable name="place">
	<xsl:value-of select="@olisCode"/>
      </xsl:variable>
      <xsl:for-each select="key('REL',@oucsCode)">
	<relation name="occupies" active="#{$code1}"
		  passive="{@passive}"/>
	<xsl:message>relation 2 <xsl:value-of select="$code1"/> -- <xsl:value-of select="@passive"/></xsl:message>
      </xsl:for-each>
    </xsl:for-each>
  </xsl:for-each>
</xsl:template>

</xsl:stylesheet>
