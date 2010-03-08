<xsl:stylesheet
    version="2.0"
    xmlns:html="http://www.w3.org/1999/xhtml"
    exclude-result-prefixes="tei html"                
    xmlns="http://www.tei-c.org/ns/1.0"
    xmlns:tei="http://www.tei-c.org/ns/1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    >

    <xsl:key name="PICS" match="tei:figure" use="substring-after(@corresp,'#')"/>
    <xsl:key name="ALLPLACES" match="tei:place[not(@type='room')]" use="1"/>
    <xsl:key name="PLACES" match="tei:place" use="@oxpID"/>
    <xsl:key name="RELS" match="tei:relation" use="substring-after(@active,'#')"/>
    <xsl:template match="/">
        <xsl:result-document href="individual/99999999.xml" method="xml"
            indent="yes">
            <place type="university">
	      <idno type="oxpID">99999999</idno>
                <placeName>University of Oxford</placeName>
                <trait type="type"><desc>Unit</desc></trait>
                <xsl:for-each select="//tei:place[@type='building' and @obnCode]">
                    <relation name="occupies"
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
	  <xsl:variable name="whatami">
	    <xsl:choose>
	      <xsl:when test="@type='college'">org</xsl:when>
	      <xsl:when test="@type='department'">org</xsl:when>
	      <xsl:when test="@type='site'">org</xsl:when>
	      <xsl:when test="@type='hall'">org</xsl:when>
	      <xsl:when test="@type='faculty'">org</xsl:when>
	      <xsl:when test="@type='servicedepartment'">org</xsl:when>
	      <xsl:when test="@type='unit'">org</xsl:when>
	      <xsl:when test="@type='museum'">org</xsl:when>
	      <xsl:when test="@type='library'">org</xsl:when>
	      <xsl:when test="@type='sublibrary'">org</xsl:when>
	      <xsl:when test="@type='poi'">org</xsl:when>
	      <xsl:when test="@type='uas'">org</xsl:when>
	      <xsl:otherwise>place</xsl:otherwise>
	    </xsl:choose>
	  </xsl:variable>
            <xsl:result-document href="individual/{@oxpID}.xml" method="xml" indent="yes">
                <xsl:element name="{$whatami}">
                    <xsl:apply-templates select="@*"/>
                    <xsl:for-each select="tei:event">
                        <xsl:choose>
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
                    <xsl:call-template name="traitMe"/>
                    <xsl:for-each select="tei:event[@type='status']">
                        <trait type="type" from="{@when}"><desc>College</desc></trait>
                    </xsl:for-each>

                    <xsl:apply-templates 
                        select="*[not(self::tei:place)]|processing-instruction()|comment()|text()"/>
                    <xsl:apply-templates select="key('RELS',@oxpID)"/>
		    <xsl:if test="count(key('PICS',@oxpID))&gt;0">
		      <note>
			<xsl:apply-templates
			    select="key('PICS',@oxpID)"/>
		      </note>
		    </xsl:if>
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
                    <xsl:apply-templates                       select="tei:place"/>

		</xsl:element>
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

    <xsl:template match="tei:place">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:call-template name="traitMe"/>
            <xsl:apply-templates
                select="*|processing-instruction()|comment()|text()"/>
        </xsl:copy>
    </xsl:template>


    <xsl:template match="*">
        <xsl:copy>
            <xsl:apply-templates 
                select="*|@*|processing-instruction()|comment()|text()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="tei:place/@type"/>

    <xsl:template match="tei:location/tei:ref">
      <note>
	<xsl:copy>
            <xsl:apply-templates 
                select="*|@*|processing-instruction()|comment()|text()"/>
	</xsl:copy>
      </note>
    </xsl:template>

    <xsl:template match="@asr_sublocation_type">
      <xsl:attribute name="type">SubLibrary</xsl:attribute>
      <xsl:attribute name="subtype" select="translate(.,' ','_')"/>
    </xsl:template>

    <xsl:template match="tei:place/@subtype">
      <xsl:attribute name="type" select="."/>
    </xsl:template>

    <xsl:template match="@olisID"/>
    <xsl:template match="@olisCode"/>
    <xsl:template match="@oucsCode"/>
    <xsl:template match="@obnCode"/>

    <xsl:template name="traitMe">
        <trait type="type">
            <xsl:choose>
                <xsl:when test="tei:event[@type='status']">
                    <xsl:attribute name="to">
                        <xsl:value-of
                            select="tei:event[@type='status']/@when"/>
                    </xsl:attribute>
		    <desc>
		      <xsl:text>Hall</xsl:text>
		    </desc>
                </xsl:when>
                <xsl:otherwise>
		  <desc>
                    <xsl:choose>
                        <xsl:when test="@type='college'">College</xsl:when>
                        <xsl:when test="@type='building'">Building</xsl:when>
                        <xsl:when test="@type='department'">Department</xsl:when>
                        <xsl:when test="@type='carpark'">Carpark</xsl:when>
                        <xsl:when test="@type='site'">Site</xsl:when>
                        <xsl:when test="@type='room'">Room</xsl:when>
                        <xsl:when test="@type='wap'">WAP</xsl:when>
                        <xsl:when test="@type='hall'">Hall</xsl:when>
                        <xsl:when test="@type='faculty'">Faculty</xsl:when>
                        <xsl:when test="@type='servicedepartment'">ServiceDepartment</xsl:when>
                        <xsl:when test="@type='unit'">Unit</xsl:when>
                        <xsl:when test="@type='museum'">Museum</xsl:when>
                        <xsl:when test="@type='library'">Library</xsl:when>
                        <xsl:when test="@type='sublibrary'">SubLibrary</xsl:when>
                        <xsl:when test="@type='poi'">Unit</xsl:when>
                        <xsl:when test="@type='uas'">Department</xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="@type"/>
                        </xsl:otherwise>
                    </xsl:choose>
		  </desc>
                </xsl:otherwise>
            </xsl:choose>

        </trait>

    </xsl:template>

    <xsl:template match="tei:figure">
      <figure>
	<xsl:apply-templates/>
      </figure>
    </xsl:template>


    <xsl:template match="tei:placeName">
	  <xsl:variable name="whatami">
	    <xsl:choose>
	      <xsl:when test="parent::tei:place/@type='college'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='department'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='site'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='hall'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='faculty'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='servicedepartment'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='unit'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='museum'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='library'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='sublibrary'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='poi'">org</xsl:when>
	      <xsl:when test="parent::tei:place/@type='uas'">org</xsl:when>
	      <xsl:otherwise>place</xsl:otherwise>
	    </xsl:choose>
	  </xsl:variable>
	  <xsl:element name="{$whatami}Name">
	    <xsl:apply-templates/>
	  </xsl:element>
    </xsl:template>



</xsl:stylesheet>
