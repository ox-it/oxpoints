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
        <xsl:result-document href="individual/99999999.xml" method="xml"
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
            <xsl:result-document href="individual/{@oxpID}.xml" method="xml" indent="yes">
                <xsl:copy>
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
                        <trait type="type" from="{@when}">College</trait>
                    </xsl:for-each>

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
                    <xsl:text>Hall</xsl:text>
                </xsl:when>
                <xsl:otherwise>
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
                        <xsl:otherwise>
                            <xsl:value-of select="@type"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:otherwise>
            </xsl:choose>
        </trait>

    </xsl:template>

</xsl:stylesheet>
