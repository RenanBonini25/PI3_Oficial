<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Action Figure</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo Fulano de Tal</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="clientes.jsp"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="games.jsp"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemConsoles"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemAcessorios"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemActionFigures"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="usuarios.jsp"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="filiais.jsp"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="venda.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="relatorio.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <a href="CadastrarActionFigure.jsp"><img class="add" src="_imagens/add.png"></a>
            <table class="tabelaResultados">
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Fabricante</th>
                    <th>Tamanho</th>
                    <th>Cor</th>
                    <th>Quantidade</th>
                    <th>Preço compra</th>
                    <th>Preço venda</th>
                    <th>Ações</th>
                </tr>
                <c:forEach items="${Listagem}" var="action">
                    <tr>
                        <td>${action.nome}</td>
                        <td>${action.descricao}</td>
                        <td>${action.fabricante}</td>
                        <td>${action.tamanho}</td>
                        <td>${action.cor}</td>
                        <td>${action.quantidade}</td>
                        <td>${action.precoCompra}</td>
                        <td>${action.precoVenda}</td>
                        <td>
                            <a href="EditarActionFigure?id=${action.id}" class="acoes">Editar</a> 
                            <a href="ExcluirActionFigure?id=${action.id}" class="acoes">Deletar</a> 
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>