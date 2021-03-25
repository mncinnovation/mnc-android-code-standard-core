# MNC Android Code Standard Boilerplate

## Introduction
This boilerplate is to optimizing get started android native project.

##  Todo List 
To use this boilerplate in new project, follow these step to match with your requirement app.
 
1. Download/ clone from git repository with branch name ``boilerplate-only``. This branch is boilerplate only without core module (core module already configured as dependencies at ``build.gradle``).
2. Open the project with Android Studio.
3. Change or replace some class/ params container, property/ variable, and API contract following the requirements.
List of TODO grouped by module are explained below.
| Module | TODO | DESCRIPTION |
|----|----|----|
| app | Rename package name | Package name is required to renamed by your own project name. <br/>- Main package name that will used as package application is in main module (app), so ``Right Click`` at this package module, ``Refactor``, choice ``Rename`` (or you can type ``shift + F6``), and than ``Rename package``. <br>- Input your own package name project |
| app | Change app name | App name is placed at resource strings values at main module (app module). So to rename it open ``res`` > ``values`` > ``strings``, find string values with name ``app_name``. Replace ``MNC Core Android`` with your own project name |
| app | Replace Base URL |  You just have to modify ``build.gradle`` at main module (app). Right there are ``flavorDimensions`` with name ``flavor``.<br/>Replace value of property (``buildConfigField``) ``BASE_URL`` with your own base url project in all flavor environment (dev and prod).
| app | Rename ``AppNameApplication`` class | Rename with project name application, for ex : MetubeApplication |
| auth | Update ``LoginRequest`` | Find class ``LoginRequest`` at module ``auth`` than update this login request data following login request API. |
| auth | Update ``LoginResponse`` | Find class ``LoginResponse`` at module ``auth`` than update this login response data following login response from API. |
| common | Rename ``TAG_API`` | Rename and change value of TAG_API |
| common | Replace ``R.raw.ca`` | R.raw.ca is an sample SSL certificate. Change parameter ``createKeyStore`` id by your own project certificate |
| common | Update ``BaseResponseModel`` class | This is base response of API following response like this.<br/> ``{ "responseCode" : "00", "responseMessage" : "Success", ... other responses }``. So if the contract from API project is different with this, you need to update it. 
| common | Update ``ConstantApi`` static values | Please to update this constant value following API contract. |
| common | Update ``UserModel`` class | Update this user model class data following business model project. |
>
> Tips! <br>
> You can find out this with looking for ``TODO comments`` (``ctrl+shift+F`` from keyboard and than type ``TODO:``)
>

4. To use and integrating ``BaseViewModel`` the ``ViewModel`` class should extend ``BaseViewModel`` .<br/>
To handle API response with BaseViewModel you can use CoroutineScope at ``handle`` on the result of API response.<br/>
