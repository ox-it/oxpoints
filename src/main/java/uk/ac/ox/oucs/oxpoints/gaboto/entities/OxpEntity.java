package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoEntity;

import net.sf.gaboto.node.annotation.BagLiteralProperty;
import net.sf.gaboto.node.annotation.BagResourceProperty;
import net.sf.gaboto.node.annotation.BagURIProperty;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.ResourceProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;
import net.sf.gaboto.node.annotation.StaticProperty;
import net.sf.gaboto.node.annotation.UnstoredProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class OxpEntity extends GabotoEntity {
  protected String name;
  protected Collection<Image> images;
  protected Image img;
  protected Collection<String> sameAss;
  protected String description;
  protected String homepage;
  protected String weblog;
  protected Collection<String> dcType;
  protected String prefLabel;
  protected Collection<String> altLabels;
  protected Collection<String> hiddenLabels;
  protected String shortLabel;
  protected String sortLabel;
  protected String acronym;
  private Collection<SKOSCollection> memberOf;
  protected String lyouAbout;
  protected String lyouAboutExecutive;
  protected String lyouAboutLeader;
  protected String lyouAboutParents;
  protected String lyouAboutSites;
  protected String lyouAboutStrategy;
  protected String lyouAcademicDepts;
  protected String lyouBusiness;
  protected String lyouBusinessIncubation;
  protected String lyouBusinessKtp;
  protected String lyouConferenceFacilities;
  protected String lyouConferences;
  protected String lyouContact;
  protected String lyouContactStaff;
  protected String lyouCourses;
  protected String lyouDepts;
  protected String lyouEvents;
  protected String lyouEventsGraduation;
  protected String lyouFoundation;
  protected String lyouFoundationCourses;
  protected String lyouFoundationCoursesEntryRequirements;
  protected String lyouFoundationProspectus;
  protected String lyouIctSupport;
  protected String lyouInternationalStudents;
  protected String lyouJobs;
  protected String lyouLegal;
  protected String lyouLegalDataProtection;
  protected String lyouLegalEnvironment;
  protected String lyouLegalEquality;
  protected String lyouLegalEthics;
  protected String lyouLegalFoi;
  protected String lyouLegalIct;
  protected String lyouLegalPolicies;
  protected String lyouLegalRegulations;
  protected String lyouLegalWebsite;
  protected String lyouNews;
  protected String lyouOpenData;
  protected String lyouOpendays;
  protected String lyouPostgraduate;
  protected String lyouPostgraduateResearch;
  protected String lyouPostgraduateResearchCourses;
  protected String lyouPostgraduateResearchCoursesEntryRequirements;
  protected String lyouPostgraduateResearchProspectus;
  protected String lyouPostgraduateTaught;
  protected String lyouPostgraduateTaughtCourses;
  protected String lyouPostgraduateTaughtCoursesEntryRequirements;
  protected String lyouPostgraduateTaughtProspectus;
  protected String lyouPress;
  protected String lyouPressFacts;
  protected String lyouPublicLectures;
  protected String lyouResearch;
  protected String lyouSearch;
  protected String lyouSpaceAccessibility;
  protected String lyouSupportDepts;
  protected String lyouUkIcoPublicationScheme;
  protected String lyouUndergraduate;
  protected String lyouUndergraduateCourses;
  protected String lyouUndergraduateCoursesEntryRequirements;
  protected String lyouUndergraduateProspectus;
  protected String lyouWebAccessibility;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
  }

  @UnstoredProperty({"http://www.w3.org/2000/01/rdf-schema#label"})
  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getName(){
    return this.name;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setName(String name){
    this.name = name;
  }

  @BagURIProperty("http://xmlns.com/foaf/0.1/depiction")
  public Collection<Image> getImages(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.images;
  }

  @BagURIProperty("http://xmlns.com/foaf/0.1/depiction")
  public void setImages(Collection<Image> images){
    if( images != null ){
      for( GabotoEntity _entity : images)
        this.removeMissingReference( _entity.getUri() );
    }

    this.images = images;
  }

  public void addImage(Image image){
    if( image != null )
      this.removeMissingReference( image.getUri() );
    if(this.images == null )
      this.images = new HashSet<Image>();
    this.images.add(image);
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/img")
  public Image getImg(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.img;
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/img")
  public void setImg(Image img){
    if( img != null )
      this.removeMissingReference( img.getUri() );
    this.img = img;
  }

  @BagResourceProperty("http://www.w3.org/2004/02/skos/core#exactMatch")
  public Collection<String> getSameAss(){
    return this.sameAss;
  }

  @BagResourceProperty("http://www.w3.org/2004/02/skos/core#exactMatch")
  public void setSameAss(Collection<String> sameAss){
    this.sameAss = sameAss;
  }

  public void addSameAs(String sameAsP){
    if(this.sameAss == null)
      setSameAss( new HashSet<String>() );
    this.sameAss.add(sameAsP);
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/description",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getDescription(){
    return this.description;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/description",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDescription(String description){
    this.description = description;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/homepage")
  public String getHomepage(){
    return this.homepage;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/homepage")
  public void setHomepage(String homepage){
    this.homepage = homepage;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/weblog")
  public String getWeblog(){
    return this.weblog;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/weblog")
  public void setWeblog(String weblog){
    this.weblog = weblog;
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/type",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getDcType(){
    return this.dcType;
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/type",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDcType(Collection<String> dcType){
    this.dcType = dcType;
  }

  public void addDcType(String dcTypeP){
    if(this.dcType == null)
      setDcType( new HashSet<String>() );
    this.dcType.add(dcTypeP);
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#prefLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getPrefLabel(){
    return this.prefLabel;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#prefLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setPrefLabel(String prefLabel){
    this.prefLabel = prefLabel;
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#altLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getAltLabels(){
    return this.altLabels;
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#altLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setAltLabels(Collection<String> altLabels){
    this.altLabels = altLabels;
  }

  public void addAltLabel(String altLabelP){
    if(this.altLabels == null)
      setAltLabels( new HashSet<String>() );
    this.altLabels.add(altLabelP);
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#hiddenLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getHiddenLabels(){
    return this.hiddenLabels;
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#hiddenLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setHiddenLabels(Collection<String> hiddenLabels){
    this.hiddenLabels = hiddenLabels;
  }

  public void addHiddenLabel(String hiddenLabelP){
    if(this.hiddenLabels == null)
      setHiddenLabels( new HashSet<String>() );
    this.hiddenLabels.add(hiddenLabelP);
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#shortLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getShortLabel(){
    return this.shortLabel;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#shortLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setShortLabel(String shortLabel){
    this.shortLabel = shortLabel;
  }

  @SimpleLiteralProperty(
    value = "http://open.vocab.org/terms/sortLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getSortLabel(){
    return this.sortLabel;
  }

  @SimpleLiteralProperty(
    value = "http://open.vocab.org/terms/sortLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setSortLabel(String sortLabel){
    this.sortLabel = sortLabel;
  }

  @SimpleLiteralProperty(
    value = "http://open.vocab.org/terms/prefAcronym",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getAcronym(){
    return this.acronym;
  }

  @SimpleLiteralProperty(
    value = "http://open.vocab.org/terms/prefAcronym",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setAcronym(String acronym){
    this.acronym = acronym;
  }

  @PassiveProperty(
    uri = "http://www.w3.org/2004/02/skos/core#member",
    entity = "SKOSCollection"
  )
  public Collection<SKOSCollection> getMemberOf(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.memberOf;
  }

  @PassiveProperty(
    uri = "http://www.w3.org/2004/02/skos/core#member",
    entity = "SKOSCollection"
  )
  private void setMemberOf(Collection<SKOSCollection> memberOf){
    this.memberOf = memberOf;
  }

  private void addMemberOf(SKOSCollection memberOfP){
    if(this.memberOf == null)
      setMemberOf( new HashSet<SKOSCollection>() );
    this.memberOf.add(memberOfP);
  }

  @ResourceProperty("http://purl.org/linkingyou/about")
  public String getLyouAbout(){
    return this.lyouAbout;
  }

  @ResourceProperty("http://purl.org/linkingyou/about")
  public void setLyouAbout(String lyouAbout){
    this.lyouAbout = lyouAbout;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-executive")
  public String getLyouAboutExecutive(){
    return this.lyouAboutExecutive;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-executive")
  public void setLyouAboutExecutive(String lyouAboutExecutive){
    this.lyouAboutExecutive = lyouAboutExecutive;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-leader")
  public String getLyouAboutLeader(){
    return this.lyouAboutLeader;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-leader")
  public void setLyouAboutLeader(String lyouAboutLeader){
    this.lyouAboutLeader = lyouAboutLeader;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-parents")
  public String getLyouAboutParents(){
    return this.lyouAboutParents;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-parents")
  public void setLyouAboutParents(String lyouAboutParents){
    this.lyouAboutParents = lyouAboutParents;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-sites")
  public String getLyouAboutSites(){
    return this.lyouAboutSites;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-sites")
  public void setLyouAboutSites(String lyouAboutSites){
    this.lyouAboutSites = lyouAboutSites;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-strategy")
  public String getLyouAboutStrategy(){
    return this.lyouAboutStrategy;
  }

  @ResourceProperty("http://purl.org/linkingyou/about-strategy")
  public void setLyouAboutStrategy(String lyouAboutStrategy){
    this.lyouAboutStrategy = lyouAboutStrategy;
  }

  @ResourceProperty("http://purl.org/linkingyou/academic-depts")
  public String getLyouAcademicDepts(){
    return this.lyouAcademicDepts;
  }

  @ResourceProperty("http://purl.org/linkingyou/academic-depts")
  public void setLyouAcademicDepts(String lyouAcademicDepts){
    this.lyouAcademicDepts = lyouAcademicDepts;
  }

  @ResourceProperty("http://purl.org/linkingyou/business")
  public String getLyouBusiness(){
    return this.lyouBusiness;
  }

  @ResourceProperty("http://purl.org/linkingyou/business")
  public void setLyouBusiness(String lyouBusiness){
    this.lyouBusiness = lyouBusiness;
  }

  @ResourceProperty("http://purl.org/linkingyou/business-incubation")
  public String getLyouBusinessIncubation(){
    return this.lyouBusinessIncubation;
  }

  @ResourceProperty("http://purl.org/linkingyou/business-incubation")
  public void setLyouBusinessIncubation(String lyouBusinessIncubation){
    this.lyouBusinessIncubation = lyouBusinessIncubation;
  }

  @ResourceProperty("http://purl.org/linkingyou/business-ktp")
  public String getLyouBusinessKtp(){
    return this.lyouBusinessKtp;
  }

  @ResourceProperty("http://purl.org/linkingyou/business-ktp")
  public void setLyouBusinessKtp(String lyouBusinessKtp){
    this.lyouBusinessKtp = lyouBusinessKtp;
  }

  @ResourceProperty("http://purl.org/linkingyou/conference-facilities")
  public String getLyouConferenceFacilities(){
    return this.lyouConferenceFacilities;
  }

  @ResourceProperty("http://purl.org/linkingyou/conference-facilities")
  public void setLyouConferenceFacilities(String lyouConferenceFacilities){
    this.lyouConferenceFacilities = lyouConferenceFacilities;
  }

  @ResourceProperty("http://purl.org/linkingyou/conferences")
  public String getLyouConferences(){
    return this.lyouConferences;
  }

  @ResourceProperty("http://purl.org/linkingyou/conferences")
  public void setLyouConferences(String lyouConferences){
    this.lyouConferences = lyouConferences;
  }

  @ResourceProperty("http://purl.org/linkingyou/contact")
  public String getLyouContact(){
    return this.lyouContact;
  }

  @ResourceProperty("http://purl.org/linkingyou/contact")
  public void setLyouContact(String lyouContact){
    this.lyouContact = lyouContact;
  }

  @ResourceProperty("http://purl.org/linkingyou/contact-staff")
  public String getLyouContactStaff(){
    return this.lyouContactStaff;
  }

  @ResourceProperty("http://purl.org/linkingyou/contact-staff")
  public void setLyouContactStaff(String lyouContactStaff){
    this.lyouContactStaff = lyouContactStaff;
  }

  @ResourceProperty("http://purl.org/linkingyou/courses")
  public String getLyouCourses(){
    return this.lyouCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/courses")
  public void setLyouCourses(String lyouCourses){
    this.lyouCourses = lyouCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/depts")
  public String getLyouDepts(){
    return this.lyouDepts;
  }

  @ResourceProperty("http://purl.org/linkingyou/depts")
  public void setLyouDepts(String lyouDepts){
    this.lyouDepts = lyouDepts;
  }

  @ResourceProperty("http://purl.org/linkingyou/events")
  public String getLyouEvents(){
    return this.lyouEvents;
  }

  @ResourceProperty("http://purl.org/linkingyou/events")
  public void setLyouEvents(String lyouEvents){
    this.lyouEvents = lyouEvents;
  }

  @ResourceProperty("http://purl.org/linkingyou/events-graduation")
  public String getLyouEventsGraduation(){
    return this.lyouEventsGraduation;
  }

  @ResourceProperty("http://purl.org/linkingyou/events-graduation")
  public void setLyouEventsGraduation(String lyouEventsGraduation){
    this.lyouEventsGraduation = lyouEventsGraduation;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation")
  public String getLyouFoundation(){
    return this.lyouFoundation;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation")
  public void setLyouFoundation(String lyouFoundation){
    this.lyouFoundation = lyouFoundation;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation-courses")
  public String getLyouFoundationCourses(){
    return this.lyouFoundationCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation-courses")
  public void setLyouFoundationCourses(String lyouFoundationCourses){
    this.lyouFoundationCourses = lyouFoundationCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation-courses-entry-requirements")
  public String getLyouFoundationCoursesEntryRequirements(){
    return this.lyouFoundationCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation-courses-entry-requirements")
  public void setLyouFoundationCoursesEntryRequirements(String lyouFoundationCoursesEntryRequirements){
    this.lyouFoundationCoursesEntryRequirements = lyouFoundationCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation-prospectus")
  public String getLyouFoundationProspectus(){
    return this.lyouFoundationProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/foundation-prospectus")
  public void setLyouFoundationProspectus(String lyouFoundationProspectus){
    this.lyouFoundationProspectus = lyouFoundationProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/ict-support")
  public String getLyouIctSupport(){
    return this.lyouIctSupport;
  }

  @ResourceProperty("http://purl.org/linkingyou/ict-support")
  public void setLyouIctSupport(String lyouIctSupport){
    this.lyouIctSupport = lyouIctSupport;
  }

  @ResourceProperty("http://purl.org/linkingyou/international-students")
  public String getLyouInternationalStudents(){
    return this.lyouInternationalStudents;
  }

  @ResourceProperty("http://purl.org/linkingyou/international-students")
  public void setLyouInternationalStudents(String lyouInternationalStudents){
    this.lyouInternationalStudents = lyouInternationalStudents;
  }

  @ResourceProperty("http://purl.org/linkingyou/jobs")
  public String getLyouJobs(){
    return this.lyouJobs;
  }

  @ResourceProperty("http://purl.org/linkingyou/jobs")
  public void setLyouJobs(String lyouJobs){
    this.lyouJobs = lyouJobs;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal")
  public String getLyouLegal(){
    return this.lyouLegal;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal")
  public void setLyouLegal(String lyouLegal){
    this.lyouLegal = lyouLegal;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-data-protection")
  public String getLyouLegalDataProtection(){
    return this.lyouLegalDataProtection;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-data-protection")
  public void setLyouLegalDataProtection(String lyouLegalDataProtection){
    this.lyouLegalDataProtection = lyouLegalDataProtection;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-environment")
  public String getLyouLegalEnvironment(){
    return this.lyouLegalEnvironment;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-environment")
  public void setLyouLegalEnvironment(String lyouLegalEnvironment){
    this.lyouLegalEnvironment = lyouLegalEnvironment;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-equality")
  public String getLyouLegalEquality(){
    return this.lyouLegalEquality;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-equality")
  public void setLyouLegalEquality(String lyouLegalEquality){
    this.lyouLegalEquality = lyouLegalEquality;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-ethics")
  public String getLyouLegalEthics(){
    return this.lyouLegalEthics;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-ethics")
  public void setLyouLegalEthics(String lyouLegalEthics){
    this.lyouLegalEthics = lyouLegalEthics;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-foi")
  public String getLyouLegalFoi(){
    return this.lyouLegalFoi;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-foi")
  public void setLyouLegalFoi(String lyouLegalFoi){
    this.lyouLegalFoi = lyouLegalFoi;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-ict")
  public String getLyouLegalIct(){
    return this.lyouLegalIct;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-ict")
  public void setLyouLegalIct(String lyouLegalIct){
    this.lyouLegalIct = lyouLegalIct;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-policies")
  public String getLyouLegalPolicies(){
    return this.lyouLegalPolicies;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-policies")
  public void setLyouLegalPolicies(String lyouLegalPolicies){
    this.lyouLegalPolicies = lyouLegalPolicies;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-regulations")
  public String getLyouLegalRegulations(){
    return this.lyouLegalRegulations;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-regulations")
  public void setLyouLegalRegulations(String lyouLegalRegulations){
    this.lyouLegalRegulations = lyouLegalRegulations;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-website")
  public String getLyouLegalWebsite(){
    return this.lyouLegalWebsite;
  }

  @ResourceProperty("http://purl.org/linkingyou/legal-website")
  public void setLyouLegalWebsite(String lyouLegalWebsite){
    this.lyouLegalWebsite = lyouLegalWebsite;
  }

  @ResourceProperty("http://purl.org/linkingyou/news")
  public String getLyouNews(){
    return this.lyouNews;
  }

  @ResourceProperty("http://purl.org/linkingyou/news")
  public void setLyouNews(String lyouNews){
    this.lyouNews = lyouNews;
  }

  @ResourceProperty("http://purl.org/linkingyou/open-data")
  public String getLyouOpenData(){
    return this.lyouOpenData;
  }

  @ResourceProperty("http://purl.org/linkingyou/open-data")
  public void setLyouOpenData(String lyouOpenData){
    this.lyouOpenData = lyouOpenData;
  }

  @ResourceProperty("http://purl.org/linkingyou/opendays")
  public String getLyouOpendays(){
    return this.lyouOpendays;
  }

  @ResourceProperty("http://purl.org/linkingyou/opendays")
  public void setLyouOpendays(String lyouOpendays){
    this.lyouOpendays = lyouOpendays;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate")
  public String getLyouPostgraduate(){
    return this.lyouPostgraduate;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate")
  public void setLyouPostgraduate(String lyouPostgraduate){
    this.lyouPostgraduate = lyouPostgraduate;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research")
  public String getLyouPostgraduateResearch(){
    return this.lyouPostgraduateResearch;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research")
  public void setLyouPostgraduateResearch(String lyouPostgraduateResearch){
    this.lyouPostgraduateResearch = lyouPostgraduateResearch;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research-courses")
  public String getLyouPostgraduateResearchCourses(){
    return this.lyouPostgraduateResearchCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research-courses")
  public void setLyouPostgraduateResearchCourses(String lyouPostgraduateResearchCourses){
    this.lyouPostgraduateResearchCourses = lyouPostgraduateResearchCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research-courses-entry-requirements")
  public String getLyouPostgraduateResearchCoursesEntryRequirements(){
    return this.lyouPostgraduateResearchCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research-courses-entry-requirements")
  public void setLyouPostgraduateResearchCoursesEntryRequirements(String lyouPostgraduateResearchCoursesEntryRequirements){
    this.lyouPostgraduateResearchCoursesEntryRequirements = lyouPostgraduateResearchCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research-prospectus")
  public String getLyouPostgraduateResearchProspectus(){
    return this.lyouPostgraduateResearchProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-research-prospectus")
  public void setLyouPostgraduateResearchProspectus(String lyouPostgraduateResearchProspectus){
    this.lyouPostgraduateResearchProspectus = lyouPostgraduateResearchProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught")
  public String getLyouPostgraduateTaught(){
    return this.lyouPostgraduateTaught;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught")
  public void setLyouPostgraduateTaught(String lyouPostgraduateTaught){
    this.lyouPostgraduateTaught = lyouPostgraduateTaught;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught-courses")
  public String getLyouPostgraduateTaughtCourses(){
    return this.lyouPostgraduateTaughtCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught-courses")
  public void setLyouPostgraduateTaughtCourses(String lyouPostgraduateTaughtCourses){
    this.lyouPostgraduateTaughtCourses = lyouPostgraduateTaughtCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught-courses-entry-requirements")
  public String getLyouPostgraduateTaughtCoursesEntryRequirements(){
    return this.lyouPostgraduateTaughtCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught-courses-entry-requirements")
  public void setLyouPostgraduateTaughtCoursesEntryRequirements(String lyouPostgraduateTaughtCoursesEntryRequirements){
    this.lyouPostgraduateTaughtCoursesEntryRequirements = lyouPostgraduateTaughtCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught-prospectus")
  public String getLyouPostgraduateTaughtProspectus(){
    return this.lyouPostgraduateTaughtProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/postgraduate-taught-prospectus")
  public void setLyouPostgraduateTaughtProspectus(String lyouPostgraduateTaughtProspectus){
    this.lyouPostgraduateTaughtProspectus = lyouPostgraduateTaughtProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/press")
  public String getLyouPress(){
    return this.lyouPress;
  }

  @ResourceProperty("http://purl.org/linkingyou/press")
  public void setLyouPress(String lyouPress){
    this.lyouPress = lyouPress;
  }

  @ResourceProperty("http://purl.org/linkingyou/press-facts")
  public String getLyouPressFacts(){
    return this.lyouPressFacts;
  }

  @ResourceProperty("http://purl.org/linkingyou/press-facts")
  public void setLyouPressFacts(String lyouPressFacts){
    this.lyouPressFacts = lyouPressFacts;
  }

  @ResourceProperty("http://purl.org/linkingyou/public-lectures")
  public String getLyouPublicLectures(){
    return this.lyouPublicLectures;
  }

  @ResourceProperty("http://purl.org/linkingyou/public-lectures")
  public void setLyouPublicLectures(String lyouPublicLectures){
    this.lyouPublicLectures = lyouPublicLectures;
  }

  @ResourceProperty("http://purl.org/linkingyou/research")
  public String getLyouResearch(){
    return this.lyouResearch;
  }

  @ResourceProperty("http://purl.org/linkingyou/research")
  public void setLyouResearch(String lyouResearch){
    this.lyouResearch = lyouResearch;
  }

  @ResourceProperty("http://purl.org/linkingyou/search")
  public String getLyouSearch(){
    return this.lyouSearch;
  }

  @ResourceProperty("http://purl.org/linkingyou/search")
  public void setLyouSearch(String lyouSearch){
    this.lyouSearch = lyouSearch;
  }

  @ResourceProperty("http://purl.org/linkingyou/space-accessibility")
  public String getLyouSpaceAccessibility(){
    return this.lyouSpaceAccessibility;
  }

  @ResourceProperty("http://purl.org/linkingyou/space-accessibility")
  public void setLyouSpaceAccessibility(String lyouSpaceAccessibility){
    this.lyouSpaceAccessibility = lyouSpaceAccessibility;
  }

  @ResourceProperty("http://purl.org/linkingyou/support-depts")
  public String getLyouSupportDepts(){
    return this.lyouSupportDepts;
  }

  @ResourceProperty("http://purl.org/linkingyou/support-depts")
  public void setLyouSupportDepts(String lyouSupportDepts){
    this.lyouSupportDepts = lyouSupportDepts;
  }

  @ResourceProperty("http://purl.org/linkingyou/uk-ico-publication-scheme")
  public String getLyouUkIcoPublicationScheme(){
    return this.lyouUkIcoPublicationScheme;
  }

  @ResourceProperty("http://purl.org/linkingyou/uk-ico-publication-scheme")
  public void setLyouUkIcoPublicationScheme(String lyouUkIcoPublicationScheme){
    this.lyouUkIcoPublicationScheme = lyouUkIcoPublicationScheme;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate")
  public String getLyouUndergraduate(){
    return this.lyouUndergraduate;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate")
  public void setLyouUndergraduate(String lyouUndergraduate){
    this.lyouUndergraduate = lyouUndergraduate;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate-courses")
  public String getLyouUndergraduateCourses(){
    return this.lyouUndergraduateCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate-courses")
  public void setLyouUndergraduateCourses(String lyouUndergraduateCourses){
    this.lyouUndergraduateCourses = lyouUndergraduateCourses;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate-courses-entry-requirements")
  public String getLyouUndergraduateCoursesEntryRequirements(){
    return this.lyouUndergraduateCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate-courses-entry-requirements")
  public void setLyouUndergraduateCoursesEntryRequirements(String lyouUndergraduateCoursesEntryRequirements){
    this.lyouUndergraduateCoursesEntryRequirements = lyouUndergraduateCoursesEntryRequirements;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate-prospectus")
  public String getLyouUndergraduateProspectus(){
    return this.lyouUndergraduateProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/undergraduate-prospectus")
  public void setLyouUndergraduateProspectus(String lyouUndergraduateProspectus){
    this.lyouUndergraduateProspectus = lyouUndergraduateProspectus;
  }

  @ResourceProperty("http://purl.org/linkingyou/web-accessibility")
  public String getLyouWebAccessibility(){
    return this.lyouWebAccessibility;
  }

  @ResourceProperty("http://purl.org/linkingyou/web-accessibility")
  public void setLyouWebAccessibility(String lyouWebAccessibility){
    this.lyouWebAccessibility = lyouWebAccessibility;
  }






  @StaticProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#allCollections")
  public Collection<SKOSCollection> getAllCollections() {
	  Collection<SKOSCollection> collections = new HashSet<SKOSCollection>();
	  if (getMemberOf() == null)
		  return collections;
	  for (SKOSCollection collection : getMemberOf()) {
		  collections.add(collection);
		  if (collection instanceof SKOSCollection)
			  collections.addAll(((SKOSCollection) collection).getAllCollections());
	  }
	  return collections;
  }

  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://www.w3.org/2004/02/skos/core#Collection";
      }

      public String getUri() {
        return "http://www.w3.org/2004/02/skos/core#member";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addMemberOf((SKOSCollection)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY name
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/title"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setName(((Literal)stmt.getObject()).getString());

    // Load BAG_URI_PROPERTY images
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://xmlns.com/foaf/0.1/depiction"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addImage((Image) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
    }

    // Load SIMPLE_URI_PROPERTY img
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/img"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setImg((Image)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load BAG_RESOURCE_PROPERTY sameAss
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#exactMatch"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(node.isURIResource()){
                this.addSameAs(((Resource) node).getURI());
            }
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY description
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/description"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setDescription(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_RESOURCE_PROPERTY homepage
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/homepage"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setHomepage(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY weblog
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/weblog"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setWeblog(((Resource) stmt.getObject()).getURI());
    }

    // Load BAG_LITERAL_PROPERTY dcType
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/dc/elements/1.1/type"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addDcType(((Literal)node).getString());
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY prefLabel
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#prefLabel"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setPrefLabel(((Literal)stmt.getObject()).getString());

    // Load BAG_LITERAL_PROPERTY altLabels
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#altLabel"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addAltLabel(((Literal)node).getString());
        }
    }

    // Load BAG_LITERAL_PROPERTY hiddenLabels
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#hiddenLabel"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addHiddenLabel(((Literal)node).getString());
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY shortLabel
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#shortLabel"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setShortLabel(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY sortLabel
    stmt = res.getProperty(snapshot.getProperty("http://open.vocab.org/terms/sortLabel"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setSortLabel(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY acronym
    stmt = res.getProperty(snapshot.getProperty("http://open.vocab.org/terms/prefAcronym"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setAcronym(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_RESOURCE_PROPERTY lyouAbout
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/about"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAbout(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouAboutExecutive
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/about-executive"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAboutExecutive(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouAboutLeader
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/about-leader"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAboutLeader(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouAboutParents
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/about-parents"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAboutParents(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouAboutSites
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/about-sites"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAboutSites(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouAboutStrategy
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/about-strategy"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAboutStrategy(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouAcademicDepts
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/academic-depts"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouAcademicDepts(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouBusiness
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/business"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouBusiness(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouBusinessIncubation
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/business-incubation"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouBusinessIncubation(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouBusinessKtp
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/business-ktp"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouBusinessKtp(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouConferenceFacilities
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/conference-facilities"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouConferenceFacilities(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouConferences
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/conferences"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouConferences(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouContact
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/contact"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouContact(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouContactStaff
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/contact-staff"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouContactStaff(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouCourses
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/courses"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouCourses(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouDepts
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/depts"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouDepts(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouEvents
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/events"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouEvents(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouEventsGraduation
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/events-graduation"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouEventsGraduation(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouFoundation
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/foundation"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouFoundation(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouFoundationCourses
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/foundation-courses"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouFoundationCourses(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouFoundationCoursesEntryRequirements
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/foundation-courses-entry-requirements"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouFoundationCoursesEntryRequirements(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouFoundationProspectus
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/foundation-prospectus"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouFoundationProspectus(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouIctSupport
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/ict-support"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouIctSupport(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouInternationalStudents
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/international-students"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouInternationalStudents(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouJobs
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/jobs"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouJobs(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegal
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegal(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalDataProtection
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-data-protection"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalDataProtection(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalEnvironment
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-environment"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalEnvironment(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalEquality
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-equality"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalEquality(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalEthics
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-ethics"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalEthics(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalFoi
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-foi"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalFoi(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalIct
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-ict"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalIct(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalPolicies
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-policies"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalPolicies(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalRegulations
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-regulations"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalRegulations(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouLegalWebsite
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/legal-website"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouLegalWebsite(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouNews
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/news"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouNews(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouOpenData
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/open-data"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouOpenData(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouOpendays
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/opendays"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouOpendays(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduate
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduate(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateResearch
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-research"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateResearch(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateResearchCourses
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-research-courses"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateResearchCourses(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateResearchCoursesEntryRequirements
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-research-courses-entry-requirements"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateResearchCoursesEntryRequirements(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateResearchProspectus
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-research-prospectus"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateResearchProspectus(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateTaught
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-taught"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateTaught(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateTaughtCourses
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-taught-courses"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateTaughtCourses(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateTaughtCoursesEntryRequirements
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-taught-courses-entry-requirements"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateTaughtCoursesEntryRequirements(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPostgraduateTaughtProspectus
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/postgraduate-taught-prospectus"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPostgraduateTaughtProspectus(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPress
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/press"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPress(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPressFacts
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/press-facts"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPressFacts(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouPublicLectures
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/public-lectures"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouPublicLectures(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouResearch
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/research"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouResearch(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouSearch
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/search"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouSearch(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouSpaceAccessibility
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/space-accessibility"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouSpaceAccessibility(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouSupportDepts
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/support-depts"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouSupportDepts(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouUkIcoPublicationScheme
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/uk-ico-publication-scheme"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouUkIcoPublicationScheme(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouUndergraduate
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/undergraduate"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouUndergraduate(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouUndergraduateCourses
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/undergraduate-courses"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouUndergraduateCourses(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouUndergraduateCoursesEntryRequirements
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/undergraduate-courses-entry-requirements"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouUndergraduateCoursesEntryRequirements(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouUndergraduateProspectus
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/undergraduate-prospectus"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouUndergraduateProspectus(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY lyouWebAccessibility
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/linkingyou/web-accessibility"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLyouWebAccessibility(((Resource) stmt.getObject()).getURI());
    }

  }
  protected List<Method> getIndirectMethodsForProperty(String propertyURI){
    List<Method> list = super.getIndirectMethodsForProperty(propertyURI);
    if(list == null)
      return indirectPropertyLookupTable.get(propertyURI);
    
    else{
      List<Method> tmp = indirectPropertyLookupTable.get(propertyURI);
      if(tmp != null)
        list.addAll(tmp);
    }
    return list;
  }

}