package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.InfluencingFactorTypeEnum;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import controller.ControllerInterface;

public class ProjectComposite extends Composite {
	private String _projectName;
	private GlossaryComposite _glossaryComposite;
	private ProjectSettingsComposite _projectSettingsComposite;
	private ProjectUseComposite _projectUseComposite;
	private TargetSpecificationComposite _targetSpecificationComposite;
	private CalculatedSpecificationsComposite _specificationsComposite;
	private CalculatedSpecificationsComposite _dataSpecificationsComposite;
	private NonCalculatedSpecificationsComposite _performanceSpecificationsComposite;
	private NonCalculatedSpecificationsComposite _qualitySpecificationsComposite;
	private InfluencingFactorComposite _influencingFactorComposite;
	private TabFolder _tabFolder;
	private TabItem _tbtmInfluencingFactors;

	/**
	 * Create the composite.
	 * @param parent parent of the composite
	 * @param style SWT-style
	 * @param controller Implemenation of ControllerInterface
	 */
	public ProjectComposite(Composite parent, int style, String projectName, ControllerInterface controller) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this._projectName = projectName;
		_tabFolder = new TabFolder(this, SWT.BOTTOM);
		_tabFolder.setTouchEnabled(true);
		_tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmProject = new TabItem(_tabFolder, SWT.NONE);
		tbtmProject.setText("Project Settings");
		this._projectSettingsComposite = new ProjectSettingsComposite(_tabFolder, SWT.None, controller);
		tbtmProject.setControl(this._projectSettingsComposite);
		
		TabItem tbtmProjectUse = new TabItem(_tabFolder, SWT.NONE);
		tbtmProjectUse.setText("Project Use");
		this._projectUseComposite = new ProjectUseComposite(_tabFolder, SWT.None, controller);
		tbtmProjectUse.setControl(this._projectUseComposite);
		
		TabItem tbtmTarget = new TabItem(_tabFolder, SWT.NONE);
		tbtmTarget.setText("Target Specification");
		this._targetSpecificationComposite = new TargetSpecificationComposite(_tabFolder, SWT.None, controller);
		tbtmTarget.setControl(this._targetSpecificationComposite);
		
		TabItem tbtmSpecifications = new TabItem(_tabFolder, SWT.NONE);
		tbtmSpecifications.setText("Function Specifications");
		this._specificationsComposite = new CalculatedSpecificationsComposite(_tabFolder, SWT.None, SpecificationTypeEnum.Function, controller);
		tbtmSpecifications.setControl(this._specificationsComposite);
		
		TabItem tbtmDataSpecifications = new TabItem(_tabFolder, SWT.NONE);
		tbtmDataSpecifications.setText("Data Specifications");
		this._dataSpecificationsComposite = new CalculatedSpecificationsComposite(_tabFolder, SWT.NONE, SpecificationTypeEnum.Data, controller);
		tbtmDataSpecifications.setControl(this._dataSpecificationsComposite);
		
		TabItem tbtmPerformanceSpecifications = new TabItem(_tabFolder, SWT.NONE);
		tbtmPerformanceSpecifications.setText("Performance Specifications");
		this._performanceSpecificationsComposite = 
		new NonCalculatedSpecificationsComposite(_tabFolder, SWT.NONE, SpecificationTypeEnum.Performance, controller);
		tbtmPerformanceSpecifications.setControl(this._performanceSpecificationsComposite);
		
		TabItem tbtmQualitySpecifications = new TabItem(_tabFolder, SWT.NONE);
		tbtmQualitySpecifications.setText("Quality Specifications");
		this._qualitySpecificationsComposite = new NonCalculatedSpecificationsComposite(_tabFolder, SWT.NONE, SpecificationTypeEnum.Quality, controller);
		tbtmQualitySpecifications.setControl(this._qualitySpecificationsComposite);
		
		_tbtmInfluencingFactors = new TabItem(_tabFolder, SWT.NONE);
		_tbtmInfluencingFactors.setText("Influencing Factors");
		this._influencingFactorComposite = new InfluencingFactorComposite(_tabFolder, SWT.NONE, controller);
		_tbtmInfluencingFactors.setControl(_influencingFactorComposite);
		
		TabItem tbtmGlossary = new TabItem(_tabFolder, SWT.NONE);
		tbtmGlossary.setText("Glossary");
		this._glossaryComposite = new GlossaryComposite(_tabFolder, SWT.None, controller);
		tbtmGlossary.setControl(this._glossaryComposite);
	}
	
	/**
	 * @return the selected glossaryentry
	 */
	public GlossaryEntry getSelectedGlossaryEntry() {
		return this._glossaryComposite.getSelectedGlossaryEntry();
	}
	
	/**
	 * refreshes the glossary of this project
	 */
	public void showGlossaryChanges() {
		this._glossaryComposite.refresh();
	}
	
	/**
	 * @return the name of this project
	 */
	public String getProjectName() {
		return this._projectName;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * makes an indicator green/red if the projectname is valid/invalid
	 * @param valid
	 */
	public void showProjectNameValidity(boolean valid) {
		this._projectSettingsComposite.showProjectNameValidity(valid);
	}
	
	/**
	 * Sets the name of this projectcomposite
	 * @param projectName new projectname
	 */
	public void setProjectName(String projectName) {
		this._projectName = projectName;
	}
	
	/**
	 * Sets data of the project
	 * @param field field to set
	 * @param value new value of the field
	 */
	@SuppressWarnings("unchecked")
	public void setData(ProjectFieldEnum field, Object value) {
		switch (field) {
		case Specifications:
			ArrayList<Specification> specifications = ((ArrayList<Specification>) value);
			this._specificationsComposite.setSpecifications(specifications);
			this._dataSpecificationsComposite.setSpecifications(specifications);
			this._performanceSpecificationsComposite.setSpecifications(specifications);
			this._qualitySpecificationsComposite.setSpecifications(specifications);
			break;
		case Glossary:
			this._glossaryComposite.setGlossary((ArrayList<GlossaryEntry>)value);
			break;
		case Author:
			this._projectSettingsComposite.setAuthor((String)value);
			break;
		case Cocomomethod:
			this._projectSettingsComposite.setCocomoMethod((String) value);
			break;
		case Company:
			this._projectSettingsComposite.setCompany((String) value);
			break;
		case LinesOfCode:
			this._projectSettingsComposite.setLOC((String) value);
			break;
		case Name:
			this._projectSettingsComposite.setName((String) value);
			break;
		case ProjectUse:
			this._projectUseComposite.setProjectUse((String) value);
			break;
		case TargetSpecification:
			this._targetSpecificationComposite.setTargetSpecification((String) value);
			break;
		case ValueAdjustmentFactor:
			this._projectSettingsComposite.setVAF((String) value);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Gets data of the project
	 * @param field field to get
	 * @return value of the field
	 */
	public String getData(ProjectFieldEnum field) {
		switch (field) {
			case GlossaryEntry:
				return this._glossaryComposite.getEntry();
			case GlossaryDescription:
				return this._glossaryComposite.getDescription();
			case Author:
				return this._projectSettingsComposite.getAuthor();
			case Cocomomethod:
				return this._projectSettingsComposite.getCocomoMethod();
			case Company:
				return this._projectSettingsComposite.getCompany();
			case LinesOfCode:
				return this._projectSettingsComposite.getLOC();
			case Name:
				return this._projectSettingsComposite.getProjectName();
			case ProjectUse:
				return this._projectUseComposite.getProjectUse();
			case TargetSpecification:
				return this._targetSpecificationComposite.getTargetSpecification();
			case ValueAdjustmentFactor:
				return this._projectSettingsComposite.getVAF();
			default:
				return "";
		}
	}
	
	/**
	 * Gets data of the selected specification
	 * @param field field to get
	 * @return value of the field
	 */
	public String getData(SpecificationFieldEnum field) {
		switch (getSelectedSpecificationType()) {
			case Function:
				return _specificationsComposite.getData(field);
			case Data:
				return _dataSpecificationsComposite.getData(field);
			case Performance:
				return _performanceSpecificationsComposite.getData(field);
			case Quality:
				return _qualitySpecificationsComposite.getData(field);
			default:
				return null;
		}
	}
	
	
	/**
	 * @return the currently selected specification
	 */
	public Specification getSelectedSpecification() {
		switch (getSelectedSpecificationType()) {
			case Data:
				return _dataSpecificationsComposite.getSelectedSpecification();
			case Function:
				return _specificationsComposite.getSelectedSpecification();
			case Performance:
				return _performanceSpecificationsComposite.getSelectedSpecification();
			case Quality:
				return _qualitySpecificationsComposite.getSelectedSpecification();
			default:
				return null;
		}
	}
	
	/**
	 * @return the type of the selected specification
	 */
	public SpecificationTypeEnum getSelectedSpecificationType() {
		switch (_tabFolder.getSelectionIndex()) {
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
	
	/**
	 * Gets the value of the specified influencingfactor
	 * @param type type of the factor
	 * @return value of the factor
	 */
	public String getData(InfluencingFactorTypeEnum type) {
		return _influencingFactorComposite.getInfluencingFactor(type);
	}
	
	/**
	 * refreshes the specifications to show changes
	 */
	public void refreshSpecifications() {
		_dataSpecificationsComposite.refresh();
		_specificationsComposite.refresh();
		_performanceSpecificationsComposite.refresh();
		_qualitySpecificationsComposite.refresh();
	}
}
