package ziraja.shared.data;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Report implements Serializable {
    private static final long serialVersionUID = -4179150374364680620L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String question;
    @Persistent
    private Integer quality;
    @Persistent
    private String rawAnswer;
    @Persistent
    private String answer;
    @Persistent
    private String doesItMean;
    @Persistent
    private String comment;
    @Persistent
    private Date timeStamp;

    public Report() {
    }

    public final String getQuestion() {
        return question;
    }

    public final void setQuestion(final String question) {
        this.question = question;
    }

    public final String getAnswer() {
        return answer;
    }

    public final void setAnswer(final String answer) {
        this.answer = answer;
    }

    public final String getDoesItMean() {
        return doesItMean;
    }

    public final void setDoesItMean(final String doesItMean) {
        this.doesItMean = doesItMean;
    }

    public final Date getTimeStamp() {
        return timeStamp;
    }

    public final void setTimeStamp(final Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public final Long getId() {
        return id;
    }

    public final String getComment() {
        return comment;
    }

    public final void setComment(final String comment) {
        this.comment = comment;
    }

    public final String getRawAnswer() {
        return rawAnswer;
    }

    public final void setRawAnswer(final String rawAnswer) {
        this.rawAnswer = rawAnswer;
    }

    public final void setQuality(final Integer quality) {
        this.quality = quality;
    }

    public final Integer getQuality() {
        return quality;
    }

    public final String toString() {
        return question;
    }

}
