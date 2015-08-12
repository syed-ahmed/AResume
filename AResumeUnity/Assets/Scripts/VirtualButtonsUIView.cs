/*============================================================================== 
 * Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved. 
 * ==============================================================================*/

using UnityEngine;
using System.Collections;
using Vuforia;

public class VirtualButtonsUIView : ISampleAppUIView {
    
    
    #region PUBLIC_PROPERTIES
    public CameraDevice.FocusMode FocusMode
    {
        get {
            return mFocusMode;
        }
        set {
            mFocusMode = value;
        }
    }
    #endregion PUBLIC_PROPERTIES
    
    #region PUBLIC_MEMBER_VARIABLES
    public event System.Action TappedToClose;
    public SampleAppUIBox mBox;
    public SampleAppUILabel mHeaderLabel;
    public SampleAppUILabel mButtonsLabel;
    public SampleAppUICheckButton mAboutButton;
    
    public SampleAppUICheckButton mButtonFour;
    public SampleAppUICheckButton mButtonTwo;
    public SampleAppUICheckButton mButtonThree;
    public SampleAppUICheckButton mButtonOne;
    
    public SampleAppUIRadioButton mVirtualButtons;
    public SampleAppUILabel mButtonLabel;
    public SampleAppUIButton mCloseButton;
    #endregion PUBLIC_MEMBER_VARIABLES
    
    #region PRIVATE_MEMBER_VARIABLES
    private CameraDevice.FocusMode mFocusMode;
    private SampleAppsUILayout mLayout;
    #endregion PRIVATE_MEMBER_VARIABLES
    
    #region PUBLIC_METHODS
    
    public void LoadView()
    {
        mLayout = new SampleAppsUILayout();
        mHeaderLabel = mLayout.AddLabel("Virtual Buttons");
        mAboutButton = mLayout.AddSimpleButton("About");
        mLayout.AddGap(14);
        mButtonLabel = mLayout.AddGroupLabel("Button");
        mButtonFour = mLayout.AddSlider_Type2("button4", true);
        mLayout.AddGap(2);
        mButtonThree = mLayout.AddSlider_Type2("button3", true);
        mLayout.AddGap(2);
        mButtonTwo = mLayout.AddSlider_Type2("button2", true);
        mLayout.AddGap(2);
        mButtonOne = mLayout.AddSlider_Type2("button1", true);
        Rect CloseButtonRect = new Rect(0, Screen.height - (100 * Screen.width) / 800.0f, Screen.width, (70.0f * Screen.width) / 800.0f);
        mCloseButton = mLayout.AddButton("Close", CloseButtonRect);
    }
    
    public void UnLoadView()
    {
        mHeaderLabel = null;
        mAboutButton = null;
        mButtonFour = null;
        mButtonTwo = null;
        mButtonOne = null;
        mButtonThree = null;
    }
    
    public void UpdateUI(bool tf)
    {
        if(!tf)
        {
            return;
        }

        mLayout.Draw();
    }

    public void OnTappedToClose ()
    {
        if(this.TappedToClose != null)
        {
            this.TappedToClose();
        }
    }
    #endregion PUBLIC_METHODS

}
