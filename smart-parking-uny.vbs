Set oShell = CreateObject("WScript.Shell")
strDesktop = oShell.SpecialFolders("Desktop")
strAppPath = "d:\UNY\Semester-2\Praktik Pemorgraman 2\smart-parking-uny-final\SmartParking.bat"
strShortcut = strDesktop & "\Smart Parking UNY.lnk"
Set oShortcut = oShell.CreateShortcut(strShortcut)
oShortcut.TargetPath = strAppPath
oShortcut.WorkingDirectory = "d:\UNY\Semester-2\Praktik Pemorgraman 2\smart-parking-uny-final"
oShortcut.WindowStyle = 1
oShortcut.IconLocation = "%SystemRoot%\system32\shell32.dll, 1"
oShortcut.Description = "Smart Parking UNY"
oShortcut.Save

Set WshShell = CreateObject("WScript.Shell")
WshShell.CurrentDirectory = CreateObject("Scripting.FileSystemObject").GetParentFolderName(WScript.ScriptFullName)
WshShell.Run "SmartParking.bat", 0, False
