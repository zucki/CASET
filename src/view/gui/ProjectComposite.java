package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.InfluencingFactorTypeEnum;
import model.data.Project;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;

import controller.Controller;
import controller.ControllerInterface;

public class ProjectComposite extends Composite {
	private TabItem tabItem;
	private String projectName;
	private GlossaryComposite glossaryComposite;
	private ProjectSettingsComposite projectSettingsComposite;
	private ProjectUseComposite projectUseComposite;
	private TargetSpecificationComposite targetSpecificationComposite;
	private CalculatedSpecificationsComposite specificationsComposite;
	private CalculatedSpecificationsComposite dataSpecificationsComposite;
	private NonCalculatedSpecificationsComposite performanceSpecificationsComposite;
	private NonCalculatedSpecificationsComposite qualitySpecificationsComposite;
	private InfluencingFactorComposite influencingFactorComposite;
	private ControllerInterface controller;
	private TabFolder tabFolder;
	private TabItem tbtmInfluencingFactors;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectComposite(Composite parent, int style, String projectName, ControllerInterface controller) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this.projectName = projectName;
		this.controller = controller;
		
		tabFolder = new TabFolder(this, SWT.BOTTOM);
		tabFolder.setTouchEnabled(true);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmProject = new TabItem(tabFolder, SWT.NONE);
		tbtmProject.setText("Project Settings");
		this.projectSettingsComposite = new ProjectSettingsComposite(tabFolder, SWT.None, controller);
		tbtmProject.setControl(this.projectSettingsComposite);
		
		TabItem tbtmProjectUse = new TabItem(tabFolder, SWT.NONE);
		tbtmProjectUse.setText("Project Use");
		this.projectUseComposite = new ProjectUseComposite(tabFolder, SWT.None, controller);
		tbtmProjectUse.setControl(this.projectUseComposite);
		
		TabItem tbtmTarget = new TabItem(tabFolder, SWT.NONE);
		tbtmTarget.setText("Target Specification");
		this.targetSpecificationComposite = new TargetSpecificationComposite(tabFolder, SWT.None, controller);
		tbtmTarget.setControl(this.targetSpecificationComposite);
		
		TabItem tbtmSpecifications = new TabItem(tabFolder, SWT.NONE);
		tbtmSpecifications.setText("Function Specifications");
		this.specificationsComposite = new CalculatedSpecificationsComposite(tabFolder, SWT.None, SpecificationTypeEnum.Function, controller);
		tbtmSpecifications.setControl(this.specificationsComposite);
		
		TabItem tbtmDataSpecifications = new TabItem(tabFolder, SWT.NONE);
		tbtmDataSpecifications.setText("Data Specifications");
		this.dataSpecificationsComposite = new CalculatedSpecificationsComposite(tabFolder, SWT.NONE, SpecificationTypeEnum.Data, controller);
		tbtmDataSpecifications.setControl(this.dataSpecificationsComposite);
		
		TabItem tbtmPerformanceSpecifications = new TabItem(tabFolder, SWT.NONE);
		tbtmPerformanceSpecifications.setText("Performance Specifications");
		this.performanceSpecificationsComposite = 
		new NonCalculatedSpecificationsComposite(tabFolder, SWT.NONE, SpecificationTypeEnum.Performance, controller);
		tbtmPerformanceSpecifications.setControl(this.performanceSpecificationsComposite);
		
		TabItem tbtmQualitySpecifications = new TabItem(tabFolder, SWT.NONE);
		tbtmQualitySpecifications.setText("Quality Specifications");
		this.qualitySpecificationsComposite = new NonCalculatedSpecificationsComposite(tabFolder, SWT.NONE, SpecificationTypeEnum.Quality, controller);
		tbtmQualitySpecifications.setControl(this.qualitySpecificationsComposite);
		
		tbtmInfluencingFactors = new TabItem(tabFolder, SWT.NONE);
		tbtmInfluencingFactors.setText("Influencing Factors");
		this.influencingFactorComposite = new InfluencingFactorComposite(tabFolder, SWT.NONE, controller);
		tbtmInfluencingFactors.setControl(influencingFactorComposite);
		
		TabItem tbtmGlossary = new TabItem(tabFolder, SWT.NONE);
		tbtmGlossary.setText("Glossary");
		this.glossaryComposite = new GlossaryComposite(tabFolder, SWT.None, controller);
		tbtmGlossary.setControl(this.glossaryComposite);
	}
	
	public GlossaryEntry getSelectedGlossaryEntry() {
		return this.glossaryComposite.getSelectedGlossaryEntry();
	}
	
	public void showGlossaryChanges() {
		this.glossaryComposite.refresh();
	}
	
	public String getProjectName() {
		return this.projectName;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void showProjectNameValidity(boolean valid) {
		this.projectSettingsComposite.showProjectNameValidity(valid);
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void setData(ProjectFieldEnum field, Object value) {
		switch (field) {
		case Specifications:
			ArrayList<Specification> specifications = ((ArrayList<Specification>) value);
			this.specificationsComposite.setSpecifications(specifications);
			this.dataSpecificationsComposite.setSpecifications(specifications);
			this.performanceSpecificationsComposite.setSpecifications(specifications);
			this.qualitySpecificationsComposite.setSpecifications(specifications);
			break;
		case Glossary:
			this.glossaryComposite.setGlossary((ArrayList<GlossaryEntry>)value);
			break;
		case Author:
			this.projectSettingsComposite.setAuthor((String)value);
			break;
		case Cocomomethod:
			this.projectSettingsComposite.setCocomoMethod((String) value);
			break;
		case Company:
			this.projectSettingsComposite.setCompany((String) value);
			break;
		case LinesOfCode:
			this.projectSettingsComposite.setLOC((String) value);
			break;
		case Name:
			this.projectSettingsComposite.setName((String) value);
			break;
		case ProjectUse:
			this.projectUseComposite.setProjectUse((String) value);
			break;
		case TargetSpecification:
			this.targetSpecificationComposite.setTargetSpecification((String) value);
			break;
		case ValueAdjustmentFactor:
			this.projectSettingsComposite.setVAF((String) value);
			break;
		}
	}
	
	public String getData(ProjectFieldEnum field) {
		switch (field) {
			case GlossaryEntry:
				return this.glossaryComposite.getEntry();
			case GlossaryDescription:
				return this.glossaryComposite.getDescription();
			case Author:
				return this.projectSettingsComposite.getAuthor();
			case Cocomomethod:
				return this.projectSettingsComposite.getCocomoMethod();
			case Company:
				return this.projectSettingsComposite.getCompany();
			case LinesOfCode:
				return this.projectSettingsComposite.getLOC();
			case Name:
				return this.projectSettingsComposite.getName();
			case ProjectUse:
				return this.projectUseComposite.getProjectUse();
			case TargetSpecification:
				return this.targetSpecificationComposite.getTargetSpecification();
			case ValueAdjustmentFactor:
				return this.projectSettingsComposite.getVAF();
			default:
				return "";
		}
	}
	
	public String getData(SpecificationFieldEnum field) {
		switch (getSelectedSpecificationType()) {
			case Function:
				return specificationsComposite.getData(field);
			case Data:
				return dataSpecificationsComposite.getData(field);
			case Performance:
				return performanceSpecificationsComposite.getData(field);
			case Quality:
				return qualitySpecificationsComposite.getData(field);
			default:
				return null;
		}
	}
	
	public Specification getSpecification() {
		switch (getSelectedSpecificationType()) {
			case Data:
				return dataSpecificationsComposite.getSpecification();
			case Function:
				return specificationsComposite.getSpecification();
			case Performance:
				return performanceSpecificationsComposite.getSpecification();
			case Quality:
				return qualitySpecificationsComposite.getSpecification();
			default:
				return null;
		}
	}
	
	public Specification getSelectedSpecification() {
		switch (getSelectedSpecificationType()) {
			case Data:
				return dataSpecificationsComposite.getSelectedSpecification();
			case Function:
				return specificationsComposite.getSelectedSpecification();
			case Performance:
				return performanceSpecificationsComposite.getSelectedSpecification();
			case Quality:
				return qualitySpecificationsComposite.getSelectedSpecification();
			default:
				return null;
		}
	}
	
	public SpecificationTypeEnum getSelectedSpecificationType() {
		switch (tabFolder.getSelectionIndex()) {
			case 3:
				return SpecificationTypeEnum.Function;
			case 4:
				return SpecificationTypeEnum.Data;
			case 5:
				return SpecificationTypeEnum.Performance;
			case 6:
				return SpecificationTypeEnum.Quality;
			default:
				return null;
		}
	}
	
	public String getData(InfluencingFactorTypeEnum type) {
		return influencingFactorComposite.getInfluencingFactor(type);
	}
	
	public void refreshSpecifications() {
		dataSpecificationsComposite.refresh();
		specificationsComposite.refresh();
		performanceSpecificationsComposite.refresh();
		qualitySpecificationsComposite.refresh();
	}
}
