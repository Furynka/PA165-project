(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{183:function(e,t,a){e.exports=a(346)},188:function(e,t,a){},346:function(e,t,a){"use strict";a.r(t);var n=a(1),r=a.n(n),l=a(10),c=a.n(l),i=(a(188),a(356)),o=a(364),m=a(351),u=a(37),s=a(11),b=a(12),d=a(13),p=a(9),f=a(363),h=a(360),y=a(17),O=a(16),j=a(347),E=j.a.Item,g=Object(p.a)(p.d,Object(p.b)({mode:"inline",items:[],labelFunction:function(e){return Object(s.get)(e,"label")},style:{},onClick:s.noop}),Object(p.g)(function(e){var t=e.items,a=e.labelFunction,r=e.defaultSelectedKey,l=e.onClick,c=e.selectedKey;return{onClick:function(e){var a=Object(s.find)(t,function(t,a){return a===Number(e.key)});a&&l(a)},defaultSelectedKeys:!Object(s.isNaN)(r)&&r>=0?[Object(s.toString)(r)]:void 0,selectedKeys:!Object(s.isNaN)(c)&&c>=0?[Object(s.toString)(c)]:void 0,children:Object(s.map)(t,function(e,t){e.items;var r=Object(d.a)(e,["items"]);return n.createElement(E,Object(b.a)({key:t},r),a(r))})}}),Object(p.c)(function(e){e.items,e.labelFunction,e.groupLabelFunction,e.defaultSelectedKey,e.selectedKey;return Object(d.a)(e,["items","labelFunction","groupLabelFunction","defaultSelectedKey","selectedKey"])}))(function(e){var t=Object(O.a)({},e);return n.createElement(j.a,Object(b.a)({},t))}),v=a(161),w=a(90),x=Object(p.a)(p.d,Object(p.e)("label","children"),Object(p.e)("type","htmlType"),Object(p.e)("transparent","ghost"),Object(p.g)(function(e){return{type:e.primary?"primary":"default",shape:e.circle?"circle":void 0}}),Object(p.c)(function(e){e.primary,e.circle;return Object(d.a)(e,["primary","circle"])}))(function(e){var t=Object(O.a)({},e);return n.createElement(w.a,t)}),k=Object(p.a)(p.d,Object(p.b)({buttonStyle:{},trigger:["click"]}),Object(p.g)(function(e){var t=e.items,a=e.label,r=e.buttonStyle,l=e.onClick;return{overlay:n.createElement(g,{items:t,onClick:l}),children:n.createElement(x,{label:a,transparent:!0,style:r})}}),Object(p.c)(function(e){return Object(O.a)({},e)}))(function(e){var t=Object(O.a)({},e);return n.createElement(v.a,t)}),S="Creatures hunting",C=h.a.Header,R=h.a.Content,B=h.a.Sider,P=Object(p.a)(p.d,f.a,Object(p.b)({items:[]}),Object(p.h)("collapsed","setCollapsed",!1),Object(p.g)(function(e){var t=e.items,a=e.history,n=e.location;return{menuProps:{onClick:function(e){return a.push(e.value)},selectedKey:Object(s.findIndex)(t,function(e){return e.value===n.pathname}),items:Object(s.map)(t,function(e){var t=e.icon,a=e.label,n=Object(d.a)(e,["icon","label"]);return Object(b.a)({label:r.a.createElement("span",null,r.a.createElement(y.a,{type:t}),r.a.createElement("span",null,a))},n)})}}}))(function(e){var t=e.children,a=e.collapsed,n=e.setCollapsed,l=e.menuProps,c=e.history;return r.a.createElement(h.a,{style:{minHeight:"100vh"}},r.a.createElement(C,{style:{padding:"0 16px",display:"flex",alignItems:"center",justifyContent:"space-between"}},r.a.createElement("h1",{style:{margin:0,color:"#fff"}},S),r.a.createElement(k,{label:r.a.createElement(y.a,{type:"user",style:{fontSize:24}}),buttonStyle:{height:48},onClick:function(e){return c.push(e.value)},items:[{value:"/profile",label:"Profile"},{value:"/",label:"Sign out"}]})),r.a.createElement(h.a,null,r.a.createElement(B,{width:150,style:{background:"#fff",minHeight:"calc(100vh - 64px)"},collapsible:!0,collapsed:a,onCollapse:function(e){return n(e)}},r.a.createElement(g,l)),r.a.createElement(R,{style:{padding:8}},t)))}),W=a(355),F=Object(p.a)(p.d,Object(p.e)("content","children"),Object(p.g)(function(e){var t=e.title,a=e.rightTitle;return{title:t||a?n.createElement("div",{style:{width:"100%",maxWidth:"100%",display:"flex",flexWrap:"wrap",justifyContent:t&&a?"space-between":t?"flex-start":"flex-end",alignItems:"center"}},t&&n.createElement("div",null,t),a&&n.createElement("div",null,a)):void 0}}),Object(p.c)(function(e){e.extra,e.rightTitle;return Object(d.a)(e,["extra","rightTitle"])}))(function(e){var t=Object(O.a)({},e);return n.createElement(W.a,t)}),K=a(362),z=K.a.Item,U=Object(p.a)(p.d,f.a,Object(p.b)({items:[]}),Object(p.g)(function(e){var t=e.items,a=e.history;return{children:Object(s.map)(t,function(e,t){return n.createElement(z,{key:t},n.createElement("span",{onClick:function(){return a.push(e.to)}},e.label))})}}),Object(p.c)(function(e){e.items;return Object(d.a)(e,["items"])}))(function(e){var t=Object(O.a)({},e);return n.createElement(K.a,t)}),N=Object(p.a)(p.d,Object(p.g)(function(e){var t=e.breadcrumb;return{title:n.createElement(U,{items:t})}}),Object(p.c)(function(e){e.breadcrumb;return Object(d.a)(e,["breadcrumb"])}))(function(e){var t=Object(O.a)({},e);return n.createElement(F,t)}),I=a(357),A=a(354),H=I.a.confirm,D=Object(p.a)(p.d,f.a,Object(p.b)({items:[],columns:[],pagination:{showSizeChanger:!0,pageSizeOptions:["10","20","50","100"]},scroll:{x:!0},onClick:s.noop,checkboxes:!0,adding:!0,editing:!0,deleting:!0}),Object(p.h)("selectedRowKeys","setSelectedRowKeys",[]),Object(p.g)(function(e){var t=e.columns,a=e.items,n=e.onClick,r=e.checkboxes,l=e.editing,c=e.selectedRowKeys,i=e.setSelectedRowKeys;return{onRow:l?function(e){return{onClick:function(){return n(e)}}}:s.noop,dataSource:Object(s.map)(a,function(e,t){var a=Object(O.a)({},e);return Object(b.a)({key:t},a)}),columns:Object(s.map)(t,function(e){var t=e.field,a=e.label,n=Object(d.a)(e,["field","label"]);return Object(b.a)({dataIndex:t,title:a},n)}),rowSelection:r?{selectedRowKeys:c,onChange:function(e){return i(e)},fixed:!0}:void 0}}),Object(p.c)(function(e){e.items;return Object(d.a)(e,["items"])}))(function(e){var t=e.adding,a=e.deleting,r=e.selectedRowKeys,l=e.setSelectedRowKeys,c=e.history,i=e.match,o=Object(d.a)(e,["adding","deleting","selectedRowKeys","setSelectedRowKeys","history","match"]);return n.createElement("div",{style:{display:"flex",flexDirection:"column"}},n.createElement("div",{style:{display:"flex",alignItems:"center",flexWrap:"wrap"}},t&&n.createElement(x,{primary:!0,label:"Add",onClick:function(){return c.push("".concat(i.path,"?add"))},style:{marginBottom:8,marginRight:8}}),a&&n.createElement(x,{label:"Delete",onClick:function(){return H({title:"Delete items",content:"Do you want to delete these items?",onOk:function(){return l([])}})},disabled:Object(s.isEmpty)(r),style:{marginBottom:8,marginRight:8}})),n.createElement(A.a,o))}),L=a(18),T=a(348),M=a(349),V=a(358),Z=a(359),J=V.a.TextArea,Y={width:"100%",maxWidth:"100%"},$=Object(p.a)(p.d,Object(p.e)("label","placeholder"),Object(p.g)(function(e){var t=e.style;return{style:t?Object(b.a)({},Y,t):Y}}))(function(e){var t=e.type,a=Object(d.a)(e,["type"]);return"number"===t?n.createElement(Z.a,a):"textarea"===t?n.createElement(J,a):n.createElement(V.a,Object(b.a)({type:t},a))}),q=function(e){var t=e.error,a=Object(d.a)(e,["error"]);return t?n.createElement("span",Object(b.a)({style:{color:"#FF4136"}},a),t):n.createElement("span",null)},G=Object(p.a)(Object(p.b)({gutter:16}))(function(e){var t=e.meta,a=t.touched,n=t.error,l=e.input,c=e.label,i=e.type,o=e.placeholder,m=e.disabled,u=e.gutter;return r.a.createElement(T.a,{gutter:u,style:{marginBottom:16}},r.a.createElement(M.a,null,r.a.createElement("strong",null,c)),r.a.createElement(M.a,null,r.a.createElement($,Object(b.a)({},l,{type:i,placeholder:o,disabled:m}))),a&&r.a.createElement(M.a,null,r.a.createElement(q,{error:n})))}),Q=Object(p.a)(f.a,Object(p.f)({onSubmit:function(e){var t=e.history;return function(e){console.log(e),t.push("/areas")}}}),Object(L.c)({form:"AreasForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit,a=e.match,n=e.history;return r.a.createElement(N,{breadcrumb:[{label:"Areas",to:"/areas"},{label:a.params.id?"Area ".concat(a.params.id):"New area"}],content:r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,null,r.a.createElement(M.a,{lg:12},r.a.createElement(T.a,null,Object(s.map)([{name:"name",label:"Name"},{name:"description",label:"Description",type:"textarea"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t},r.a.createElement(L.a,Object(b.a)({component:G},a)))})))),r.a.createElement("div",{style:{width:"100%",display:"flex",flexWrap:"wrap"}},Object(s.map)([{label:"Ulo\u017eit a zav\u0159\xedt",type:"submit",primary:!0,style:{marginRight:8,marginBottom:8}},{label:"Zru\u0161it",onClick:function(){return n.push("/areas")},style:{marginRight:8,marginBottom:8}}],function(e,t){return r.a.createElement(x,Object(b.a)({key:t},e))})))})}),X=function(e){var t=e.match,a=e.location,n=e.history,l=Object(d.a)(e,["match","location","history"]);return t.url===a.pathname?"?add"===a.search?r.a.createElement(Q,l):r.a.createElement(N,{breadcrumb:[{label:"Areas"}],content:r.a.createElement("div",null,r.a.createElement(D,{onClick:function(e){return n.push("/areas/".concat(e.id))},items:Object(s.map)([1,2,3,4,5,6,7,8,9,10],function(e){return{id:e,name:"Area ".concat(e),description:"Area ".concat(e," description.")}}),columns:[{field:"name",label:"Name"},{field:"description",label:"Description"}]}))}):r.a.createElement(m.a,{path:"".concat(t.url,"/:id"),render:function(){return r.a.createElement(Q,l)}})},_=h.a.Header,ee=h.a.Content,te=Object(p.a)(p.d)(function(e){var t=e.children;return r.a.createElement(h.a,{style:{minHeight:"100vh"}},r.a.createElement(_,{style:{padding:"0 16px",display:"flex",alignItems:"center",justifyContent:"center"}},r.a.createElement("h1",{style:{margin:0,color:"#fff"}},S)),r.a.createElement(h.a,null,r.a.createElement(ee,null,t)))}),ae=a(361),ne=a(129),re=a.n(ne),le=a(178),ce=a(352),ie=Object(p.a)(p.d,Object(p.b)({bodyStyle:{},centered:!0,closable:!0,content:void 0,maskClosable:!0,style:{},title:"",visible:!1,zIndex:99999,okButtonLabel:"OK",cancelButtonLabel:"Cancel"}),Object(p.e)("content","children"),Object(p.c)(function(e){var t=e.title,a=e.closable,r=e.footer,l=e.onCancel,c=e.onOk,i=e.children,o=e.bodyStyle,m=e.okButtonLabel,u=e.cancelButtonLabel,s=Object(d.a)(e,["title","closable","footer","onCancel","onOk","children","bodyStyle","okButtonLabel","cancelButtonLabel"]);return Object(b.a)({bodyStyle:{padding:0},title:null,closable:!1,footer:null,onCancel:l,children:n.createElement("div",null,(t||a)&&n.createElement("div",{style:{borderBottom:"1px solid #e8e8e8",display:"flex",flexWrap:"nowrap",justifyContent:"center",alignItems:"flex-start",padding:"10px 16px"}},n.createElement("div",{style:{display:"flex",alignItems:"center",width:a?"calc(100% - 38px)":"100%",maxWidth:a?"calc(100% - 38px)":"100%",wordBreak:"break-word",fontSize:16,fontWeight:500,lineHeight:"22px",color:"rgba(0,0,0,.85)"}},t),a&&n.createElement(x,{label:n.createElement("div",{style:{width:"100%",height:"100%",display:"flex",justifyContent:"center",alignItems:"center"}},n.createElement(y.a,{type:"close",style:{fontSize:16}})),style:{marginLeft:8,padding:0,width:30,height:24},onClick:l||ce.a})),n.createElement("div",{style:Object(b.a)({padding:16,wordBreak:"break-word"},o)},i),null!==r&&n.createElement("div",{style:{borderTop:"1px solid #e8e8e8"}},r||n.createElement("div",{style:{width:"100%",display:"flex",justifyContent:"flex-end",alignItems:"center",flexWrap:"wrap",padding:"10px 16px 0px 0px"}},n.createElement(x,{label:u,onClick:l||ce.a,style:{margin:"0px 0px 10px 16px"}}),n.createElement(x,{primary:!0,label:m,onClick:c||ce.a,style:{margin:"0px 0px 10px 16px"}}))))},s)}))(function(e){var t=Object(O.a)({},e);return n.createElement(I.a,t)}),oe=Object(p.a)(p.d,Object(p.b)({onOk:function(){return!0}}),Object(p.h)("visible","setVisible",!1),Object(p.c)(function(e){var t=e.label,a=e.primary,n=e.buttonProps,r=e.visible,l=e.setVisible,c=e.title,i=e.onOk,o=e.content,m=e.modalProps,u=Object(d.a)(e,["label","primary","buttonProps","visible","setVisible","title","onOk","content","modalProps"]);return Object(b.a)({buttonProps:Object(b.a)({},n,{label:t,primary:a,onClick:function(){return l(!0)}}),modalProps:Object(b.a)({},m,{title:c,visible:r,content:o,onOk:function(){var e=Object(le.a)(re.a.mark(function e(){var t=arguments;return re.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,i.apply(void 0,t);case 2:if(!e.sent){e.next=4;break}l(!1);case 4:case"end":return e.stop()}},e,this)}));return function(){return e.apply(this,arguments)}}(),onCancel:function(){return l(!1)}})},u)}))(function(e){var t=e.modalProps,a=e.buttonProps,r=Object(d.a)(e,["modalProps","buttonProps"]);return n.createElement("div",r,n.createElement(ie,t),n.createElement(x,a))}),me=Object(p.a)(f.a,Object(p.f)({onSubmit:function(){return function(e){return console.log(e),ae.a.success("You can sign in now!"),!0}}}),Object(L.c)({form:"RegisterForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit;return r.a.createElement(oe,{label:"Sign up",title:"Sign up",buttonProps:{style:{marginBottom:8,marginRight:8}},content:r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,null,Object(s.map)([{name:"name",label:"Username"},{name:"firstname",label:"First name"},{name:"surname",label:"Surname"},{name:"email",label:"Email"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t},r.a.createElement(L.a,Object(b.a)({component:G},a)))}))),onOk:t})}),ue=Object(p.a)(f.a,Object(p.f)({onSubmit:function(e){var t=e.history;return function(e){var a=e.username,n=e.password;console.log("Sign In:",a,n),t.push("/home")}}}),Object(L.c)({form:"AuthenticationForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit;return r.a.createElement("div",{style:{height:"calc(100vh - 64px)",width:"100vw",display:"flex",justifyContent:"center",alignItems:"center"}},r.a.createElement("form",{onSubmit:t,style:{width:400,maxWidth:"calc(100% - 2em)",padding:"1em",display:"flex",flexDirection:"column"}},Object(s.map)([{name:"username",label:"Username"},{name:"password",label:"Password",type:"password"}],function(e){var t=e.name,a=Object(d.a)(e,["name"]);return r.a.createElement(L.a,Object(b.a)({key:t,component:G,name:t},a))}),r.a.createElement("div",{style:{width:"100%",display:"flex",flexWrap:"wrap"}},r.a.createElement(x,{label:"Sign in",type:"submit",primary:!0,style:{marginBottom:8,marginRight:8}}),r.a.createElement(me,null))))}),se=function(){return r.a.createElement(te,null,r.a.createElement(ue,null))},be=function(){return r.a.createElement(N,{breadcrumb:[{label:"Home"}],content:r.a.createElement("div",null,r.a.createElement("h1",null,"Welcome to ",S,"!"))})},de=Object(p.a)(f.a,Object(p.f)({onSubmit:function(e){var t=e.history;return function(e){console.log(e),t.push("/monsters")}}}),Object(L.c)({form:"MonstersForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit,a=e.match,n=e.history;return r.a.createElement(N,{breadcrumb:[{label:"Monsters",to:"/monsters"},{label:a.params.id?"Monster ".concat(a.params.id):"New monster"}],content:r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,{gutter:16},Object(s.map)([{name:"name",label:"Username"},{name:"height",label:"Height",type:"number"},{name:"weight",label:"Weight",type:"number"},{name:"power",label:"Power",type:"number"},{name:"agility",label:"Agility",type:"number"},{name:"speed",label:"Speed",type:"number"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t,lg:12},r.a.createElement(L.a,Object(b.a)({component:G},a)))})),r.a.createElement("div",{style:{width:"100%",display:"flex"}},Object(s.map)([{label:"Ulo\u017eit a zav\u0159\xedt",type:"submit",primary:!0,style:{marginRight:8,marginBottom:8}},{label:"Zru\u0161it",onClick:function(){return n.push("/monsters")},style:{marginRight:8,marginBottom:8}}],function(e,t){return r.a.createElement(x,Object(b.a)({key:t},e))})))})}),pe=function(e){var t=e.match,a=e.location,n=e.history,l=Object(d.a)(e,["match","location","history"]);return t.url===a.pathname?"?add"===a.search?r.a.createElement(de,l):r.a.createElement(N,{breadcrumb:[{label:"Monsters"}],content:r.a.createElement("div",null,r.a.createElement(D,{onClick:function(e){return n.push("/monsters/".concat(e.id))},items:Object(s.map)([1,2,3,4,5,6,7,8,9,10],function(e){return{id:e,name:"Monster ".concat(e),height:100*e,weight:100*e,power:100*e,agility:100*e,speed:100*e}}),columns:[{field:"name",label:"Name"},{field:"height",label:"Height"},{field:"weight",label:"Weight"},{field:"power",label:"Power"},{field:"agility",label:"Agility"},{field:"speed",label:"Speed"}]}))}):r.a.createElement(m.a,{path:"".concat(t.url,"/:id"),render:function(){return r.a.createElement(de,l)}})},fe=a(353),he=Object(p.a)(f.a,Object(p.f)({onSubmit:function(){return function(e){console.log(e),ae.a.success("Profile updated!")}}}),Object(L.c)({form:"ProfileForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit;return r.a.createElement("div",null,r.a.createElement("h2",null,"Edit profile"),r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,null,r.a.createElement(M.a,{lg:12},r.a.createElement(T.a,null,Object(s.map)([{name:"name",label:"Username",disabled:!0},{name:"firstname",label:"First name"},{name:"surname",label:"Surname"},{name:"email",label:"Email"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t},r.a.createElement(L.a,Object(b.a)({component:G},a)))})))),r.a.createElement("div",{style:{width:"100%",display:"flex"}},Object(s.map)([{label:"Update profile",type:"submit",primary:!0,style:{marginRight:8,marginBottom:8}}],function(e,t){return r.a.createElement(x,Object(b.a)({key:t},e))}))))}),ye=Object(p.a)(f.a,Object(p.f)({onSubmit:function(){return function(e){console.log(e),ae.a.success("Password changed!")}}}),Object(L.c)({form:"PasswordForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit;return r.a.createElement("div",null,r.a.createElement("h2",null,"Password change"),r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,null,r.a.createElement(M.a,{lg:12},r.a.createElement(T.a,null,Object(s.map)([{name:"password",label:"Password",type:"password"},{name:"password2",label:"Password again",type:"password"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t},r.a.createElement(L.a,Object(b.a)({component:G},a)))})))),r.a.createElement("div",{style:{width:"100%",display:"flex"}},Object(s.map)([{label:"Change password",type:"submit",primary:!0,style:{marginRight:8,marginBottom:8}}],function(e,t){return r.a.createElement(x,Object(b.a)({key:t},e))}))))}),Oe=function(){return r.a.createElement(N,{breadcrumb:[{label:"Profile"}],content:r.a.createElement("div",null,r.a.createElement(he,{initialValues:{name:"Username",firstname:"Jan",surname:"Nov\xe1k",email:"jan.novak@gmail.com"}}),r.a.createElement(fe.a,null),r.a.createElement(ye,null))})},je=Object(p.a)(f.a,Object(p.f)({onSubmit:function(e){var t=e.history;return function(e){console.log(e),t.push("/users")}}}),Object(L.c)({form:"UsersForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit,a=e.match,n=e.history;return r.a.createElement(N,{breadcrumb:[{label:"Users",to:"/users"},{label:a.params.id?"User ".concat(a.params.id):"New user"}],content:r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,null,r.a.createElement(M.a,{lg:12},r.a.createElement(T.a,null,Object(s.map)([{name:"name",label:"Username",disabled:!0},{name:"firstname",label:"First name"},{name:"surname",label:"Surname"},{name:"email",label:"Email"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t},r.a.createElement(L.a,Object(b.a)({component:G},a)))})))),r.a.createElement("div",{style:{width:"100%",display:"flex",flexWrap:"wrap"}},Object(s.map)([{label:"Ulo\u017eit a zav\u0159\xedt",type:"submit",primary:!0,style:{marginRight:8,marginBottom:8}},{label:"Zru\u0161it",onClick:function(){return n.push("/users")},style:{marginRight:8,marginBottom:8}}],function(e,t){return r.a.createElement(x,Object(b.a)({key:t},e))})))})}),Ee=function(e){var t=e.match,a=e.location,n=e.history,l=Object(d.a)(e,["match","location","history"]);return t.url===a.pathname?"?add"===a.search?r.a.createElement(je,l):r.a.createElement(N,{breadcrumb:[{label:"Users"}],content:r.a.createElement("div",null,r.a.createElement(D,{onClick:function(e){return n.push("/users/".concat(e.id))},items:Object(s.map)([1,2,3,4,5,6,7,8,9,10],function(e){return{id:e,name:"User ".concat(e),firstname:"First name",surname:"Surname",email:"email@email.com"}}),columns:[{field:"name",label:"Username"},{field:"firstname",label:"First name"},{field:"surname",label:"Surname"},{field:"email",label:"Email"}]}))}):r.a.createElement(m.a,{path:"".concat(t.url,"/:id"),render:function(){return r.a.createElement(je,l)}})},ge=Object(p.a)(f.a,Object(p.f)({onSubmit:function(e){var t=e.history;return function(e){console.log(e),t.push("/weapons")}}}),Object(L.c)({form:"WeaponsForm",enableReinitialize:!0}))(function(e){var t=e.handleSubmit,a=e.match,n=e.history;return r.a.createElement(N,{breadcrumb:[{label:"Weapons",to:"/weapons"},{label:a.params.id?"Weapon ".concat(a.params.id):"New weapon"}],content:r.a.createElement("form",{onSubmit:t},r.a.createElement(T.a,null,r.a.createElement(M.a,{lg:12},r.a.createElement(T.a,null,Object(s.map)([{name:"name",label:"Name"},{name:"description",label:"Description",type:"textarea"}],function(e,t){var a=Object(O.a)({},e);return r.a.createElement(M.a,{key:t},r.a.createElement(L.a,Object(b.a)({component:G},a)))})))),r.a.createElement("div",{style:{width:"100%",display:"flex",flexWrap:"wrap"}},Object(s.map)([{label:"Ulo\u017eit a zav\u0159\xedt",type:"submit",primary:!0,style:{marginRight:8,marginBottom:8}},{label:"Zru\u0161it",onClick:function(){return n.push("/weapons")},style:{marginRight:8,marginBottom:8}}],function(e,t){return r.a.createElement(x,Object(b.a)({key:t},e))})))})}),ve=function(e){var t=e.match,a=e.location,n=e.history,l=Object(d.a)(e,["match","location","history"]);return t.url===a.pathname?"?add"===a.search?r.a.createElement(ge,l):r.a.createElement(N,{breadcrumb:[{label:"Weapons"}],content:r.a.createElement("div",null,r.a.createElement(D,{onClick:function(e){return n.push("/weapons/".concat(e.id))},items:Object(s.map)([1,2,3,4,5,6,7,8,9,10],function(e){return{id:e,name:"Weapon ".concat(e),description:"Weapon ".concat(e," description.")}}),columns:[{field:"name",label:"Name"},{field:"description",label:"Description"}]}))}):r.a.createElement(m.a,{path:"".concat(t.url,"/:id"),render:function(){return r.a.createElement(ge,l)}})};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));var we=a(20),xe=a(180),ke={sample:{}},Se=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:ke,t=arguments.length>1?arguments[1]:void 0;switch(t.type){case"CONSTANT":return Object(b.a)({},e,t.payload);default:return e}},Ce=Object(we.c)({form:L.b,app:Se});a(344);c.a.render(r.a.createElement(function(e){var t=e.store,a=[{path:"/home",label:"Home",icon:"home",component:be},{path:"/users",label:"Users",icon:"user",component:Ee},{path:"/monsters",label:"Monsters",icon:"usb",component:pe},{path:"/weapons",label:"Weapons",icon:"thunderbolt",component:ve},{path:"/areas",label:"Areas",icon:"environment",component:X}];return r.a.createElement(u.a,{store:t},r.a.createElement(i.a,{basename:"/pa165"},r.a.createElement(o.a,null,r.a.createElement(m.a,{exact:!0,path:"/",component:se}),r.a.createElement(P,{items:Object(s.map)(a,function(e){return{value:e.path,label:e.label,icon:e.icon}})},Object(s.map)(a,function(e,t){var a=e.path,n=e.component;return r.a.createElement(m.a,{key:t,path:a,component:n})}),r.a.createElement(m.a,{path:"/profile",component:Oe})))))},{store:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},t=[xe.a];return(0,we.d)(we.a.apply(void 0,t))(we.e)(Ce,e)}()}),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}},[[183,2,1]]]);
//# sourceMappingURL=main.ac0264d8.chunk.js.map