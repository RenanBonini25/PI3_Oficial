<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Funcionários</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
        <script>
            function exclusao()
            {
                alert("Usuário excluído com sucesso!");
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
            <a href="${pageContext.request.contextPath}/CadastrarUsuario"><img class="add" src="_imagens/add.png"></a>
            <table class="tabelaResultados">
                <tr>
                    <td><strong>Nome</strong></td>
                    <td><strong>CPF</strong></td>
                    <td><strong>Username</strong></td>
                    <td><strong>Setor</strong></td>
                    <td><strong>Filial</strong></td>
                    <td><strong>Permissão</strong></td>
                    <td><strong>Ações</strong></td>
                </tr>
                <c:forEach items="${Listagem}" var="usuario">
                    <tr>
                        <td>${usuario.nome}</td>
                        <td>${usuario.cpf}</td>
                        <td>${usuario.userName}</td>
                        <td>${usuario.setor}</td>
                        <td>${usuario.filial}</td>
                        <td>${usuario.permissoes.get(0).getNome()}</td>

                        <td>
                            <a href="EditarUsuario?id=${usuario.id}" class="acoes">Editar</a> 
                            <a href="ExcluirUsuario?id=${usuario.id}" class="acoes" onclick=exclusao()>Deletar</a> 
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>
