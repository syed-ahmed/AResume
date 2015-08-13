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

		if (vb.VirtualButtonName == "button1") {
			string url = m_ProjectLinks[0];
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			jo.Call ("openWebsite", url);
		}

		if (vb.VirtualButtonName == "button2") {
			string url = m_ProjectLinks[1];
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			jo.Call ("openWebsite", url);
		}

		if (vb.VirtualButtonName == "button3") {
			string url = m_ProjectLinks[2];
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			jo.Call ("openWebsite", url);
		}

		if (vb.VirtualButtonName == "button4") {
			string address = m_ProjectLinks[3];
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			jo.Call ("sendEmail", address);
		}

		if (vb.VirtualButtonName == "button5") {
			string number = m_ProjectLinks[4];
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			jo.Call ("callPhone", number);
		}

		if (vb.VirtualButtonName == "button6") {
			string number = m_ProjectLinks[5];
			AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
			AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");
			jo.Call ("openProjects");
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
	}
			
			
	private bool IsValid()
	{
		// Check the materials and teapot have been set:
		return  m_ProjectLinks != null;
	}
	
	#endregion // PUBLIC_METHODS
}
