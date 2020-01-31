/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import com.coryneregnet7.controller.RegulationView;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mariana
 */
@Entity
@Immutable
@Table(name = "predicted_regulatory_interaction_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PredictedRegulatoryInteractionView.findAll", query = "SELECT o FROM PredictedRegulatoryInteractionView o order by o.genome, o.tf")
    , @NamedQuery(name = "PredictedRegulatoryInteractionView.findByGenome", query = "SELECT o FROM PredictedRegulatoryInteractionView o WHERE o.genome = :genome ORDER BY o.genome, o.tf")
    , @NamedQuery(name = "PredictedRegulatoryInteractionView.findByGene", query = "SELECT o FROM PredictedRegulatoryInteractionView o WHERE o.tf = :gene or o.tg = :gene")
})
public class PredictedRegulatoryInteractionView implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;
    @JoinColumn(name = "organism", referencedColumnName = "id")
    @ManyToOne
    private Organism organism;
    @JoinColumn(name = "tf", referencedColumnName = "id")
    @ManyToOne
    private Gene tf;
    @Column(name = "tf_locus_tag")
    private String tfLocusTag;
    @JoinColumn(name = "source_tf", referencedColumnName = "id")
    @ManyToOne
    private Gene sourceTf;
    @JoinColumn(name = "tg", referencedColumnName = "id")
    @ManyToOne
    private Gene tg;
    @Column(name = "tg_locus_tag")
    private String tgLocusTag;
    @JoinColumn(name = "source_tg", referencedColumnName = "id")
    @ManyToOne
    private Gene sourceTg;
    @Column(name = "regulator_density")
    private Integer regulatorDensity;
    @Column(name = "operon")
    private String operon;
    @Column(name = "pos")
    private String pos;
    @Column(name = "role")
    private String role;
    @Column(name = "binding_site")
    private String bindingSite;
    @Column(name = "pvalue")
    private String pvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    public Gene getTf() {
        return tf;
    }

    public void setTf(Gene tf) {
        this.tf = tf;
    }

    public String getTfLocusTag() {
        return tfLocusTag;
    }

    public void setTfLocusTag(String tfLocusTag) {
        this.tfLocusTag = tfLocusTag;
    }

    public Gene getTg() {
        return tg;
    }

    public void setTg(Gene tg) {
        this.tg = tg;
    }

    public String getTgLocusTag() {
        return tgLocusTag;
    }

    public void setTgLocusTag(String tgLocusTag) {
        this.tgLocusTag = tgLocusTag;
    }

    public String getOperon() {
        return operon;
    }

    public void setOperon(String operon) {
        this.operon = operon;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBindingSite() {
        return bindingSite;
    }

    public void setBindingSite(String bindingSite) {
        this.bindingSite = bindingSite;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Integer getRegulatorDensity() {
        return regulatorDensity;
    }

    public void setRegulatorDensity(Integer regulatorDensity) {
        this.regulatorDensity = regulatorDensity;
    }

    public Gene getSourceTf() {
        return sourceTf;
    }

    public void setSourceTf(Gene sourceTf) {
        this.sourceTf = sourceTf;
    }

    public Gene getSourceTg() {
        return sourceTg;
    }

    public void setSourceTg(Gene sourceTg) {
        this.sourceTg = sourceTg;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public RegulationView getRegulationView(PredictedRegulatoryInteractionView riView) {
        RegulationView regulationView = new RegulationView(riView.getId(), riView.getTg(), riView.getTf(), riView.getRole(),
                "predicted", riView.getBindingSite(), null, null,
                riView.getOperon(), riView.getOrganism(), new BigDecimal(riView.getPvalue()), new Long(riView.getRegulatorDensity()));

        return regulationView;
    }

    public ArrayList<RegulationView> getRegulationViewList(List<PredictedRegulatoryInteractionView> riViews) {
        ArrayList<RegulationView> regulationViewList = new ArrayList<>();
        RegulationView regulationView = new RegulationView();

        for (PredictedRegulatoryInteractionView riView : riViews) {
            System.out.println(riView.toString());
            System.out.println(riView.getPvalue());

            if (riView.getTf().getRole() == null) {

            }
            regulationView = new RegulationView(riView.getId(), riView.getTg(), riView.getTf(), riView.getRole(),
                    "predicted", riView.getBindingSite(), null, null,
                    riView.getOperon(), riView.getOrganism(), new BigDecimal(riView.getPvalue()), new Long(riView.getRegulatorDensity()));
            regulationViewList.add(regulationView);
        }

        return regulationViewList;
    }

    @Override
    public String toString() {
        return "PredictedRegulatoryInteractionView{" + "id=" + id + ", genome=" + genome.getId() + ", organism=" + organism + ", tf=" + tf + ", tfLocusTag=" + tfLocusTag + ", sourceTf=" + sourceTf + ", tg=" + tg + ", tgLocusTag=" + tgLocusTag + ", sourceTg=" + sourceTg + ", regulatorDensity=" + regulatorDensity + ", operon=" + operon + ", pos=" + pos + ", role=" + role + ", bindingSite=" + bindingSite + ", pvalue=" + pvalue + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredictedRegulatoryInteractionView)) {
            return false;
        }
        PredictedRegulatoryInteractionView other = (PredictedRegulatoryInteractionView) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
