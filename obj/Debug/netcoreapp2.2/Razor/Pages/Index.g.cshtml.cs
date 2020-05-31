#pragma checksum "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/Index.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "3a1188fefb2084e1438dc6024598981fdca32b4f"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(WebApp1.Pages.Pages_Index), @"mvc.1.0.razor-page", @"/Pages/Index.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.RazorPages.Infrastructure.RazorPageAttribute(@"/Pages/Index.cshtml", typeof(WebApp1.Pages.Pages_Index), null)]
namespace WebApp1.Pages
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#line 1 "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/_ViewImports.cshtml"
using Microsoft.AspNetCore.Identity;

#line default
#line hidden
#line 2 "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/_ViewImports.cshtml"
using WebApp1;

#line default
#line hidden
#line 3 "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/_ViewImports.cshtml"
using WebApp1.Data;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"3a1188fefb2084e1438dc6024598981fdca32b4f", @"/Pages/Index.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"843a1cd35a110d560a38d670cdd6217bfa162b57", @"/Pages/_ViewImports.cshtml")]
    public class Pages_Index : global::Microsoft.AspNetCore.Mvc.RazorPages.Page
    {
        #line hidden
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.HeadTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper;
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.BodyTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            BeginContext(102, 36, true);
            WriteLiteral("\n<!doctype html>\n <html lang=\"en\">\n ");
            EndContext();
            BeginContext(138, 600, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("head", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "3a1188fefb2084e1438dc6024598981fdca32b4f3819", async() => {
                BeginContext(144, 587, true);
                WriteLiteral(@"
   <meta charset=""UTF-8"">
     <meta name=""viewport"" content=""width=device-width, initial-scale=1.0"">
     <meta http-equiv=""X-UA-Compatible"" content=""ie=edge"">
     <title>Canvas</title>
     <link rel=""stylesheet"" href=""/lib/bootstrap/dist/css/bootstrap.css"" />
     <link rel=""stylesheet"" href=""css/site.css"" />
     <script src=""lib/signalr/dist/browser/signalr.js""></script>
     <script src=""https://code.jquery.com/jquery.js""></script>
     <!-- <script src=""js/canvas.js""></script> -->
     <script src = ""js/requests.js""></script>
     <script src = ""js/addList.js""></script>
 ");
                EndContext();
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.HeadTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            EndContext();
            BeginContext(738, 40, true);
            WriteLiteral("\n <!-- dotnet run -p WebApp1.csproj -->\n");
            EndContext();
#line 21 "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/Index.cshtml"
  if (SignInManager.IsSignedIn(User))
    {
        

#line default
#line hidden
            BeginContext(831, 1, true);
            WriteLiteral(" ");
            EndContext();
            BeginContext(832, 657, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("body", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "3a1188fefb2084e1438dc6024598981fdca32b4f5922", async() => {
                BeginContext(838, 131, true);
                WriteLiteral("\n     <h1><strong>Create a new canvas or open an old one!</strong></h1>\n     <br>\n     <h2>Create a new Canvas</h2>\n     <br>\n     ");
                EndContext();
                BeginContext(969, 254, false);
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "3a1188fefb2084e1438dc6024598981fdca32b4f6433", async() => {
                    BeginContext(975, 241, true);
                    WriteLiteral("\n       <h3>Enter an existing canvas title or create a new one:</h3>\n       <input type=\"text\" id = \"textFieldCreate\"/>\n       <input type=\"button\" value=\"Submit\" onclick = \"submitCreate()\"/> <!--window.location.href=\'/canvas.html\';-->\n     ");
                    EndContext();
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
                __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                EndContext();
                BeginContext(1223, 259, true);
                WriteLiteral(@"
     <br>
     <h2>Canvases that already exist</h2>
     <div id = ""alreadyExist"">        
        <!-- <ul> -->          
          <!-- <li></li> -->        
          <!-- </ul> -->      
      </div>
     <!-- <script src=""Pages/index.js""></script> -->
 ");
                EndContext();
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.BodyTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            EndContext();
            BeginContext(1489, 1, true);
            WriteLiteral("\n");
            EndContext();
#line 43 "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/Index.cshtml"

    }
    else
    {

#line default
#line hidden
            BeginContext(1512, 8, true);
            WriteLiteral("        ");
            EndContext();
            BeginContext(1520, 98, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("body", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "3a1188fefb2084e1438dc6024598981fdca32b4f9512", async() => {
                BeginContext(1526, 85, true);
                WriteLiteral("\n            <p>Hello! Please login or create an account to get drawing!</p>\n        ");
                EndContext();
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.BodyTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_BodyTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            EndContext();
            BeginContext(1618, 1, true);
            WriteLiteral("\n");
            EndContext();
#line 50 "/Users/joshuaostroff314/Documents/GitHub/csc-210-final-project-group8/WhiteBoard/Pages/Index.cshtml"
    }

#line default
#line hidden
            BeginContext(1625, 9, true);
            WriteLiteral(" </html>\n");
            EndContext();
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public UserManager<IdentityUser> UserManager { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public SignInManager<IdentityUser> SignInManager { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<Pages_Index> Html { get; private set; }
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.ViewDataDictionary<Pages_Index> ViewData => (global::Microsoft.AspNetCore.Mvc.ViewFeatures.ViewDataDictionary<Pages_Index>)PageContext?.ViewData;
        public Pages_Index Model => ViewData.Model;
    }
}
#pragma warning restore 1591
