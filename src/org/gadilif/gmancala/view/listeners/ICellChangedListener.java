package org.gadilif.gmancala.view.listeners;

/**
 * @author Gadi
 *
 */
public interface ICellChangedListener {
	/**
	 * @param cellId the changed cell
	 * @param newValue the new value
	 */
	void cellChanged(int cellId, int newValue);
}
