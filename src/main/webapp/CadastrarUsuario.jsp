<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Cadastro de Usuário</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
        <script>
            function cadastro()
            {
                alert("Usuário cadastrado com sucesso!");
            }
        </script>
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo, ${usuario.nome}</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="${pageContext.request.contextPath}/ListagemClientes"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="${pageContext.request.contextPath}/ListagemGames"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemConsoles"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemAcessorios"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemActionFigures"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="${pageContext.request.contextPath}/ListagemUsuarios"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="${pageContext.request.contextPath}/ListagemFiliais"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="Vendas_ProcurarCliente.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="${pageContext.request.contextPath}/GerarRelatorios.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                    <a href="${pageContext.request.contextPath}/Logout"><li id="listaMenu"><img src="_imagens/power.png">Logout</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <form action="${pageContext.request.contextPath}/CadastrarUsuario" method="post" id="formularioCadastro">
                <input type="text" name="txtNome" placeholder="NOME" class="formulario">
                </br></br>
                <input type="text" name="txtCpf" placeholder="CPF - 111.111.111-11" maxlength="14" class="formulario">
                </br></br>
                <input type="text" name="txtUsername" placeholder="USERNAME" maxlength="14" class="formulario">
                </br></br>
                <input type="text" name="txtSenha" placeholder="SENHA" maxlength="14" class="formulario">
                </br></br>
                <select name="Filial">
                                    <option value="null">FILIAL</option>
                                    <c:forEach var="nomeFilial" items="${listaFiliais}">
					<option value="${nomeFilial.nome}">${nomeFilial.nome}</option>
                                    </c:forEach>
				</select><br><br>
                <select name="Setor" class="formSelect">
                    <option value="null">SETOR</option>
                    <option value="Informatica">INFORMÁTICA</option>
                    <option value="Vendas">VENDAS</option>
                    <option value="Backoffice">BACKOFFICE</option>
                </select>
                <select name="Permissao" class="formSelect">
                    <option value="null">PERMISSÃO</option>
                    <option value="Básico">BÁSICO</option>
                    <option value="Gerente">GERENTE</option>
                </select>
                </br></br>
                <input type="submit" value="SALVAR" id="botao" onclick=cadastro()>
            </form>
        </div>
    </body>
</html>
