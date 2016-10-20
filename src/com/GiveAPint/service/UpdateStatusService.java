/**
 * 
 */
package com.GiveAPint.service;

import com.GiveAPint.dto.UpdateUserStatusDTO;

/**
 * This interface consists of methods for updating the user's healthStatus and his/her lastDonatedDate.
 * @author vutukuri
 *
 */
public interface UpdateStatusService {
	
	public UpdateUserStatusDTO UpdateUserStatus( UpdateUserStatusDTO user);

}
