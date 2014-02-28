package org.xezz.wow.combatlog.model;

import org.xezz.wow.log.helper.IdleType;

import java.nio.file.Path;

/**
 * User: Xezz
 * Date: 27.02.14
 * Time: 18:00
 */
public class LogModel {
    private Path combatLogLocation;
    private Path outputDirectoryLocation;
    private boolean createSubdirectory = false;
    private boolean deleteInputFileAfterProcessing = false;
    private int idleTime = 3;
    private IdleType idleType = IdleType.MINUTES;

    public IdleType getIdleType() {
        return idleType;
    }

    public void setIdleType(IdleType idleType) {
        this.idleType = idleType;
    }

    public Path getCombatLogLocation() {
        return combatLogLocation;
    }

    public void setCombatLogLocation(Path combatLogLocation) {
        this.combatLogLocation = combatLogLocation;
    }

    public Path getOutputDirectoryLocation() {
        return outputDirectoryLocation;
    }

    public void setOutputDirectoryLocation(Path outputDirectoryLocation) {
        this.outputDirectoryLocation = outputDirectoryLocation;
    }

    public boolean isCreateSubdirectory() {
        return createSubdirectory;
    }

    public void setCreateSubdirectory(boolean createSubdirectory) {
        this.createSubdirectory = createSubdirectory;
    }

    public boolean isDeleteInputFileAfterProcessing() {
        return deleteInputFileAfterProcessing;
    }

    public void setDeleteInputFileAfterProcessing(boolean deleteInputFileAfterProcessing) {
        this.deleteInputFileAfterProcessing = deleteInputFileAfterProcessing;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }
}
