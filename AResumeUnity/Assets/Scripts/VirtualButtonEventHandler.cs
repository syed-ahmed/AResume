/*============================================================================== 
 * Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved. 
 * ==============================================================================*/

using UnityEngine;
using System.Collections.Generic;
using Vuforia;

/// <summary>
/// This class implements the IVirtualButtonEventHandler interface and
/// contains the logic to swap materials for the teapot model depending on what 
/// virtual button has been pressed.
/// </summary>
public class VirtualButtonEventHandler : MonoBehaviour,
IVirtualButtonEventHandler
{
	#region PUBLIC_MEMBER_VARIABLES
	
	/// <summary>
	/// The materials that will be set for the teapot model
	/// </summary>
	public string[] m_ProjectLinks;
	
	#endregion // PUBLIC_MEMBER_VARIABLES
	
	
	
	#region PRIVATE_MEMBER_VARIABLES
	
	private List<string> mActiveLinks;
	
	#endregion // PRIVATE_MEMBER_VARIABLES
	
	
	
	#region UNITY_MONOBEHAVIOUR_METHODS
	
	void Start()
	{
		// Register with the virtual buttons TrackableBehaviour
		VirtualButtonBehaviour[] vbs = GetComponentsInChildren<VirtualButtonBehaviour>();
		for (int i = 0; i < vbs.Length; ++i)
		{
			vbs[i].RegisterEventHandler(this);
		}
		
		// The list of active materials
		mActiveLinks = new List<string>();
	}
	
	#endregion // UNITY_MONOBEHAVIOUR_METHODS
	
	
	
	#region PUBLIC_METHODS
	
	/// <summary>
	/// Called when the virtual button has just been pressed:
	/// </summary>
	public void OnButtonPressed(VirtualButtonAbstractBehaviour vb)
	{
		Debug.Log("OnButtonPressed::" + vb.VirtualButtonName);
		
		if (!IsValid())
		{
			return;
		}
		
		// Add the material corresponding to this virtual button
		// to the active material list:
		switch (vb.VirtualButtonName)
		{
		case "button1":
			mActiveLinks.Add(m_ProjectLinks[0]);
			break;
			
		case "button2":
			mActiveLinks.Add(m_ProjectLinks[1]);
			break;
			
		case "button3":
			mActiveLinks.Add(m_ProjectLinks[2]);
			break;
		}
		
		// Apply the new material:
		if (mActiveLinks.Count > 0) {
			Application.OpenURL (mActiveLinks [mActiveLinks.Count - 1]);
		}

		if (vb.VirtualButtonName == "button4") {
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			// Invoke the "showMessage" method in our Android Plugin Activity
			jo.Call ("callPhone");
		}
	}
				
		/// <summary>
		/// Called when the virtual button has just been released:
		/// </summary>
	public void OnButtonReleased(VirtualButtonAbstractBehaviour vb)
		{
		if (!IsValid())
		{
			return;
		}
		
		// Remove the material corresponding to this virtual button
		// from the active material list:
		switch (vb.VirtualButtonName)
		{
		case "button1":
			mActiveLinks.Remove(m_ProjectLinks[0]);
			break;
			
		case "button2":
			mActiveLinks.Remove(m_ProjectLinks[1]);
			break;
			
		case "button3":
			mActiveLinks.Remove(m_ProjectLinks[2]);
			break;
		}
		
		// Apply the next active material, or apply the default material:
		// Apply the new material:
		if (mActiveLinks.Count > 0) {
			Application.OpenURL (mActiveLinks [mActiveLinks.Count - 1]);
		}
		
		if (vb.VirtualButtonName == "button4") {
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			// Invoke the "showMessage" method in our Android Plugin Activity
			jo.Call ("callPhone");
		}
	}
			
			
	private bool IsValid()
	{
		// Check the materials and teapot have been set:
		return  m_ProjectLinks != null && m_ProjectLinks.Length == 4;
	}
	
	#endregion // PUBLIC_METHODS
}
